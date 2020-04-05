package com.soufang.esproject.service.house;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.soufang.esproject.base.HouseSort;
import com.soufang.esproject.base.HouseStatus;
import com.soufang.esproject.base.HouseSubscribeStatus;
import com.soufang.esproject.base.LoginUserUtil;
import com.soufang.esproject.entity.*;
import com.soufang.esproject.repository.*;
import com.soufang.esproject.service.ServiceMultiResult;
import com.soufang.esproject.service.ServiceResult;
import com.soufang.esproject.service.search.ISearchService;
import com.soufang.esproject.web.dto.HouseDTO;
import com.soufang.esproject.web.dto.HouseDetailDTO;
import com.soufang.esproject.web.dto.HousePictureDTO;
import com.soufang.esproject.web.dto.HouseSubscribeDTO;
import com.soufang.esproject.web.form.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.MappedSuperclass;
import javax.persistence.criteria.Predicate;
import java.util.*;

/**
 * Description: es-project
 * Create by liangxifeng on 19-5-13
 */
@Service
public class HouseServiceImpl implements IHouseService {
    //@Autowired
    private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private HouseReposity houseReposity;
    @Autowired
    private HouseDetailResposity houseDetailResposity;
    @Autowired
    private HousePictureResposity housePictureResposity;
    @Autowired
    private HouseTagRepository houseTagRepository;
    @Autowired
    private SubwayRepository subwayRepository;
    @Autowired
    private HouseSubscribeRespository subscribeRespository;
    @Autowired
    private SubwayStationRepository subwayStationRepository;
    @Value("${qiniu.cdn.prefix}")
    private String cdnPrefix;
    @Autowired
    private IQiNiuService qiNiuService;
    @Autowired
    private ISearchService searchService;

    /**
     * 新增房源信息
     * @param houseForm
     * @return
     */
    @Override
    public ServiceResult<HouseDTO> save(HouseForm houseForm) {
        House house = new House();
        modelMapper.map(houseForm,house);
        Date now = new Date();
        house.setCreateTime(now);
        house.setLastUpdateTime(now);
        house.setAdminId(1L);
        house = houseReposity.save(house);

        HouseDetail houseDetail = new HouseDetail();
        houseDetail.setHouseId(house.getId());
        houseDetail = houseDetailResposity.save(houseDetail);

        List<HousePicture> pictures = gengeratePicture(houseForm,house.getId());
        Iterable<HousePicture> housePictures = housePictureResposity.save(pictures);

        HouseDTO houseDTO = modelMapper.map(house,HouseDTO.class);
        HouseDetailDTO detailDTO = modelMapper.map(houseDetail,HouseDetailDTO.class);
        houseDTO.setHouseDetail(detailDTO);

        List<HousePictureDTO> pictureDTOS = new ArrayList<>();
        housePictures.forEach(housePicture -> pictureDTOS.add(modelMapper.map(housePicture,HousePictureDTO.class)));
        houseDTO.setPictures(pictureDTOS);
        houseDTO.setCover(this.cdnPrefix + houseDTO.getCityEnName());


//        List<String> tags = houseForm.getTags();
//        if(tags != null || !tags.isEmpty()){
//            List<HouseTag> houseTags = new ArrayList<>();
//            for (String tag : tags) {
//                houseTags.add(new HouseTag(house.getId(),tag));
//            }
//            houseTagRepository.save(houseTags);
//            houseDTO.setTags(tags);
//        }
        return new ServiceResult<HouseDTO>(true,null,houseDTO);
    }

    /**
     * 更新房源信息
     * @param houseForm
     * @return
     */
    @Override
    @Transactional
    public ServiceResult update(HouseForm houseForm) {
        House house = this.houseReposity.findOne(houseForm.getId());
        if (house == null) {
            return ServiceResult.notFound();
        }

        HouseDetail detail = this.houseDetailResposity.findByHouseId(house.getId());
        if (detail == null) {
            return ServiceResult.notFound();
        }

        ServiceResult wrapperResult = warpperDetailInfo(detail, houseForm);
        if (wrapperResult != null) {
            return wrapperResult;
        }

        houseDetailResposity.save(detail);

        List<HousePicture> pictures = gengeratePicture(houseForm, houseForm.getId());
        housePictureResposity.save(pictures);

        if (houseForm.getCover() == null) {
            houseForm.setCover(house.getCover());
        }

        modelMapper.map(houseForm, house);
        house.setLastUpdateTime(new Date());
        houseReposity.save(house);
        //如果审核通过则穿件es索引
        if (house.getStatus() == HouseStatus.PASSED.getValue()) {
            searchService.index(house.getId());
        }

        return ServiceResult.success();
    }

    @Override
    public ServiceResult removePhoto(Long id) {
        HousePicture picture = housePictureResposity.findOne(id);
        if (picture == null) {
            return ServiceResult.notFound();
        }

        try {
            Response response = this.qiNiuService.delete(picture.getPath());
            if (response.isOK()) {
                housePictureResposity.delete(id);
                return ServiceResult.success();
            } else {
                return new ServiceResult(false, response.error);
            }
        } catch (QiniuException e) {
            e.printStackTrace();
            return new ServiceResult(false, e.getMessage());
        }
    }

    /**
     * 更新封面图片信息
     * @param coverId
     * @param targetId
     * @return
     */
    @Override
    @Transactional
    public ServiceResult updateCover(Long coverId, Long targetId) {
        HousePicture cover = housePictureResposity.findOne(coverId);
        if (cover == null) {
            return ServiceResult.notFound();
        }

        houseReposity.updateCover(targetId, cover.getPath());
        return ServiceResult.success();
    }

    @Override
    @Transactional
    public ServiceResult addTag(Long houseId, String tag) {
        House house = houseReposity.findOne(houseId);
        if (house == null) {
            return ServiceResult.notFound();
        }

        HouseTag houseTag = houseTagRepository.findByNameAndHouseId(tag, houseId);
        if (houseTag != null) {
            return new ServiceResult(false, "标签已存在");
        }

        houseTagRepository.save(new HouseTag(houseId, tag));
        return ServiceResult.success();
    }

    @Override
    @Transactional
    public ServiceResult removeTag(Long houseId, String tag) {
        House house = houseReposity.findOne(houseId);
        if (house == null) {
            return ServiceResult.notFound();
        }

        HouseTag houseTag = houseTagRepository.findByNameAndHouseId(tag, houseId);
        if (houseTag == null) {
            return new ServiceResult(false, "标签不存在");
        }

        houseTagRepository.delete(houseTag.getId());
        return ServiceResult.success();
    }
    @Override
    @Transactional
    public ServiceResult updateStatus(Long id, int status) {
        House house = houseReposity.findOne(id);
        if (house == null) {
            return ServiceResult.notFound();
        }

        if (house.getStatus() == status) {
            return new ServiceResult(false, "状态没有发生变化");
        }

        if (house.getStatus() == HouseStatus.RENTED.getValue()) {
            return new ServiceResult(false, "已出租的房源不允许修改状态");
        }

        if (house.getStatus() == HouseStatus.DELETE.getValue()) {
            return new ServiceResult(false, "已删除的资源不允许操作");
        }

        houseReposity.updateStatus(id, status);

        // 上架更新索引 其他情况都要删除索引
        if (status == HouseStatus.PASSED.getValue()) {
            searchService.index(id);
        } else {
            searchService.remove(id);
        }
        return ServiceResult.success();
    }

    /**
     * 通过房屋主键从数据库中查询房屋信息
     * @param houseIds 房屋主键list
     * @return
     */
    private List<HouseDTO> wrapperHouseResult(List<Long> houseIds) {
        List<HouseDTO> result = new ArrayList<>();

        Map<Long, HouseDTO> idToHouseMap = new HashMap<>();
        Iterable<House> houses = houseReposity.findAll(houseIds);
        houses.forEach(house -> {
            HouseDTO houseDTO = modelMapper.map(house, HouseDTO.class);
            houseDTO.setCover(this.cdnPrefix + house.getCover());
            idToHouseMap.put(house.getId(), houseDTO);
        });
        //通过房屋主机查询房屋详情以及其他详细信息
        wrapperHouseList(houseIds, idToHouseMap);

        // 矫正顺序
        for (Long houseId : houseIds) {
            result.add(idToHouseMap.get(houseId));
        }
        return result;
    }

    /**
     * 房屋列表查询搜索分页
     * @param searchBody
     * @return
     */
    @Override
    public ServiceMultiResult<HouseDTO> adminQuery(DatatableSearch searchBody) {
        List<HouseDTO> houseDTOS = new ArrayList<>();

        Sort sort = new Sort(Sort.Direction.fromString(searchBody.getDirection()), searchBody.getOrderBy());
        int page = searchBody.getStart() / searchBody.getLength();
        Pageable pageable = new PageRequest(page, searchBody.getLength(), sort);

        Specification<House> specification = (root,query,cb)->{
            Predicate predicate = cb.equal(root.get("adminId"), 2);
            predicate = cb.and(predicate,cb.notEqual(root.get("status"), HouseStatus.DELETE.getValue()));
            if (searchBody.getCity() != null) {
                predicate = cb.and(predicate, cb.equal(root.get("cityEnName"), searchBody.getCity()));
            }

            if (searchBody.getStatus() != null) {
                predicate = cb.and(predicate, cb.equal(root.get("status"), searchBody.getStatus()));
            }

            if (searchBody.getCreateTimeMin() != null) {
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("createTime"), searchBody.getCreateTimeMin()));
            }

            if (searchBody.getCreateTimeMax() != null) {
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("createTime"), searchBody.getCreateTimeMax()));
            }

            if (searchBody.getTitle() != null) {
                predicate = cb.and(predicate, cb.like(root.get("title"), "%" + searchBody.getTitle() + "%"));
            }
            return predicate;
        };


        Page<House> houses = houseReposity.findAll(specification,pageable);

        //Iterable<House> houses = houseReposity.findAll();
        houses.forEach(house -> {
            HouseDTO houseDTO = modelMapper.map(house,HouseDTO.class);
            houseDTO.setCover(this.cdnPrefix + house.getCover());
            houseDTOS.add(houseDTO);
        });
        return new ServiceMultiResult<>(houses.getTotalElements(),houseDTOS);
    }

    /**
     * 查询一条完整的房源信息
     * @param id house表主键
     * @return
     */
    @Override
    public ServiceResult<HouseDTO> findCompleteOne(Long id) {
        //查询房屋表信息
        House house = houseReposity.findOne(id);
        if(house == null){
            return ServiceResult.notFound();
        }
        //通过房屋主键查询房屋详情表信息
        HouseDetail detail = houseDetailResposity.findAllByHouseId(id);
        HouseDetailDTO detailDTO = modelMapper.map(detail,HouseDetailDTO.class);
        List<HousePicture> pictures = housePictureResposity.findAllByHouseId(id);
        //将实体信息转为给客户端显示的DTO格式数据
        List<HousePictureDTO> pictrueDTOs = new ArrayList<>();
        pictures.forEach(pictrue->{
            HousePictureDTO pictureDTO = modelMapper.map(pictrue,HousePictureDTO.class);
            pictrueDTOs.add(pictureDTO);
        });
        //查询房屋标签信息通过房屋主键
        List<HouseTag> tags = houseTagRepository.findAllByHouseId(id);
        List<String> taglist = new ArrayList<>();
        for (HouseTag tag : tags) {
            taglist.add(tag.getName());
        }
        //将房屋实体信息转为给客户端显示的DTO格式数据
        HouseDTO reslut = modelMapper.map(house,HouseDTO.class);
        reslut.setHouseDetail(detailDTO);
        reslut.setPictures(pictrueDTOs);
        reslut.setTags(taglist);

        //查询房源预约信息
        if (LoginUserUtil.getLoginUserId() > 0) { //已登录用户
            HouseSubscribe subscribe = subscribeRespository.findByHouseIdAndUserId(house.getId(),LoginUserUtil
                    .getLoginUserId());
            if (subscribe != null) {
                //修改房源的预约状态
                reslut.setSubscribeStatus(subscribe.getStatus());
            }

        }

        ServiceResult<HouseDTO> serviceResult = ServiceResult.of(reslut);
        return serviceResult;
    }


    /**
     * 前台 http://localhost:8090/rent/house?cityEnName=bj 点击搜索触发
     * @param rentSearch
     * @return
     */
    @Override
    public ServiceMultiResult<HouseDTO> query(RentSearch rentSearch) {
        //如果存在搜索关键词,则先在es中搜索,如果es中存在houseIds, 则通过es中拿到的houseId List,去数据库搜苏详细的房屋信息
        if (rentSearch.getKeywords() != null && !rentSearch.getKeywords().isEmpty()) {
            ServiceMultiResult<Long> serviceResult = searchService.query(rentSearch);
            if (serviceResult.getTotal() == 0) {
                return new ServiceMultiResult<>(0, new ArrayList<>());
            }

            return new ServiceMultiResult<>(serviceResult.getTotal(), wrapperHouseResult(serviceResult.getResult()));
        }
        //如果没有搜索关键词,则从数据库直接返回结果
        return simpleQuery(rentSearch);

    }

    /**
     * 基于es查询房源信息--百度地图所用
     * @param mapSearch
     * @return
     */
    @Override
    public ServiceMultiResult<HouseDTO> wholeMapQuery(MapSearch mapSearch) {
        ServiceMultiResult<Long> serviceResult = searchService.mapQuery(mapSearch.getCityEnName(), mapSearch.getOrderBy(), mapSearch.getOrderDirection(), mapSearch.getStart(), mapSearch.getSize());

        if (serviceResult.getTotal() == 0) {
            return new ServiceMultiResult<>(0, new ArrayList<>());
        }
        List<HouseDTO> houses = wrapperHouseResult(serviceResult.getResult());
        return new ServiceMultiResult<>(serviceResult.getTotal(), houses);
    }

    /**
     * 精确地图查询, 用户将缩放地图或者鼠标双击放大地图到某个区域, 通过地图的左上角和右下角经纬度显示地图信息
     * @return
     */
    @Override
    public ServiceMultiResult<HouseDTO> boundMapQuery(MapSearch mapSearch) {
        ServiceMultiResult<Long> serviceResult = searchService.mapQuery(mapSearch);
        if (serviceResult.getTotal() == 0) {
            return new ServiceMultiResult<>(0, new ArrayList<>());
        }

        List<HouseDTO> houses = wrapperHouseResult(serviceResult.getResult());
        return new ServiceMultiResult<>(serviceResult.getTotal(), houses);
    }

    /**
     * 加入预约清单
     * @param houseId
     * @return
     */
    @Override
    @Transactional
    public ServiceResult addSubscribeOrder(Long houseId) {
        Long userId = LoginUserUtil.getLoginUserId();
        HouseSubscribe subscribe = subscribeRespository.findByHouseIdAndUserId(houseId, userId);
        if (subscribe != null) {
            return new ServiceResult(false, "已加入预约");
        }

        House house = houseReposity.findOne(houseId);
        if (house == null) {
            return new ServiceResult(false, "查无此房");
        }

        subscribe = new HouseSubscribe();
        Date now = new Date();
        subscribe.setCreateTime(now);
        subscribe.setLastUpdateTime(now);
        subscribe.setUserId(userId);
        subscribe.setHouseId(houseId);
        //加入预约清单
        subscribe.setStatus(HouseSubscribeStatus.IN_ORDER_LIST.getValue());
        subscribe.setAdminId(house.getAdminId());
        subscribeRespository.save(subscribe);
        return ServiceResult.success();
    }

    /**
     * 预约看房
     * @param houseId
     * @param orderTime
     * @param telephone
     * @param desc
     * @return
     */
    @Override
    @Transactional
    public ServiceResult subscribe(Long houseId, Date orderTime, String telephone, String desc) {
        Long userId = LoginUserUtil.getLoginUserId();
        HouseSubscribe subscribe = subscribeRespository.findByHouseIdAndUserId(houseId, userId);
        if (subscribe == null) {
            return new ServiceResult(false, "无预约记录");
        }

        if (subscribe.getStatus() != HouseSubscribeStatus.IN_ORDER_LIST.getValue()) {
            return new ServiceResult(false, "无法预约");
        }

        subscribe.setStatus(HouseSubscribeStatus.IN_ORDER_TIME.getValue());
        subscribe.setLastUpdateTime(new Date());
        subscribe.setTelephone(telephone);
        subscribe.setDesc(desc);
        subscribe.setOrderTime(orderTime);
        subscribeRespository.save(subscribe);
        return ServiceResult.success();
    }


    /**
     * 取消预约
     * @param houseId 房源主键
     * @return
     */
    @Override
    @Transactional
    public ServiceResult cancelSubscribe(Long houseId) {
        Long userId = LoginUserUtil.getLoginUserId();
        HouseSubscribe subscribe = subscribeRespository.findByHouseIdAndUserId(houseId, userId);
        if (subscribe == null) {
            return new ServiceResult(false, "无预约记录");
        }

        subscribeRespository.delete(subscribe.getId());
        return ServiceResult.success();
    }
    /**
     * 管理员查询预约接口
     * @param start
     * @param size
     * @return
     */
    @Override
    public ServiceMultiResult<Pair<HouseDTO, HouseSubscribeDTO>> findSubscribeList(int start, int size) {
        //因为后台登录没有调通，所以将登录id写死为2
        Long userId = LoginUserUtil.getLoginUserId();
        userId = 2L;
        System.out.println("====管理员登录用户id="+userId);
        Pageable pageable = new PageRequest(start / size, size, new Sort(Sort.Direction.DESC, "orderTime"));

        Page<HouseSubscribe> page = subscribeRespository.findAllByAdminIdAndStatus(userId, HouseSubscribeStatus.IN_ORDER_TIME.getValue(), pageable);

        return wrapper(page);
    }

    /**
     * 完成预约看房
     * @param houseId
     * @return
     */
    @Override
    @Transactional
    public ServiceResult finishSubscribe(Long houseId) {
        Long adminId = LoginUserUtil.getLoginUserId();
        adminId = 2L;
        HouseSubscribe subscribe = subscribeRespository.findByHouseIdAndAdminId(houseId, adminId);
        if (subscribe == null) {
            return new ServiceResult(false, "无预约记录");
        }

        subscribeRespository.updateStatus(subscribe.getId(), HouseSubscribeStatus.FINISH.getValue());
        houseReposity.updateWatchTimes(houseId);
        return ServiceResult.success();
    }

    /**
     * 通过数据库查询房屋信息
     * @param rentSearch
     * @return
     */
    private ServiceMultiResult<HouseDTO> simpleQuery(RentSearch rentSearch) {
        //return simpleQuery(rentSearch);
        //Sort sort = new Sort(Sort.Direction.DESC,"lastUpdateTime");
        Sort sort = HouseSort.generateSort(rentSearch.getOrderBy(),rentSearch.getOrderDirection());
        int page = rentSearch.getStart() / rentSearch.getSize();
        Pageable pageable = new PageRequest(page,rentSearch.getSize(),sort);
        Specification<House> specification = (root,criteriaQuery,cirteriaBuilder)->{
            Predicate predicate = cirteriaBuilder.equal(root.get("status"),HouseStatus.PASSED.getValue());
            predicate = cirteriaBuilder.and(predicate,cirteriaBuilder.equal(root.get("cityEnName"),rentSearch.getCityEnName()));
            if ( HouseSort.DISTANCE_TO_SUBWAY_KEY.equals(rentSearch.getOrderBy()) ) {
                predicate = cirteriaBuilder.and(predicate,cirteriaBuilder.gt(root.get(HouseSort.DISTANCE_TO_SUBWAY_KEY),-1));
            }
            return predicate;
        };
        Page<House> houses = houseReposity.findAll(specification,pageable);
        List<HouseDTO> houseDTOs = new ArrayList<>();
        List<Long> houseIds = new ArrayList<>();
        Map<Long,HouseDTO> idHouseMap = new HashMap<>();
        houses.forEach(house -> {
            HouseDTO houseDTO = modelMapper.map(house,HouseDTO.class);
            houseDTO.setCover(this.cdnPrefix + house.getCover());
            houseDTOs.add(houseDTO);
            houseIds.add(house.getId());
            idHouseMap.put(house.getId(),houseDTO);
        });
        wrapperHouseList(houseIds,idHouseMap);
        return new ServiceMultiResult<>(houses.getTotalElements(),houseDTOs);
    }

    /**
     * 渲染详细信息 及 标签
     * @param houseIds
     * @param idToHouseMap
     */
    private void wrapperHouseList(List<Long> houseIds, Map<Long, HouseDTO> idToHouseMap) {
        List<HouseDetail> details = houseDetailResposity.findAllByHouseIdIn(houseIds);
        details.forEach(houseDetail -> {
            HouseDTO houseDTO = idToHouseMap.get(houseDetail.getHouseId());
            HouseDetailDTO detailDTO = modelMapper.map(houseDetail, HouseDetailDTO.class);
            houseDTO.setHouseDetail(detailDTO);
        });

        List<HouseTag> houseTags = houseTagRepository.findAllByHouseIdIn(houseIds);
        houseTags.forEach(houseTag -> {
            HouseDTO house = idToHouseMap.get(houseTag.getHouseId());
            house.getTags().add(houseTag.getName());
        });
    }

    private List<HousePicture> gengeratePicture(HouseForm houseForm, Long houseId){
        List<HousePicture> pictures = new ArrayList<>();
        if(houseForm.getPhotos() == null || houseForm.getPhotos().isEmpty()) {
            return pictures;
        }
        for (PhotoForm photoForm : houseForm.getPhotos()) {
            HousePicture picture = new HousePicture();
            picture.setHouseId(houseId);
            picture.setCdnPrefix(cdnPrefix);
            picture.setPath(photoForm.getPath());
            picture.setWidth(photoForm.getWidth());
            picture.setHeight(photoForm.getHeight());
            pictures.add(picture);
        }
        return pictures;

    }

    /**
     * 房屋详情
     * @param houseDetail
     * @param houseForm
     * @return
     */
    private ServiceResult<HouseDTO> warpperDetailInfo(HouseDetail houseDetail, HouseForm houseForm){
        Subway subway = subwayRepository.findOne(houseForm.getSubwayLineId());
        if(subway == null)
        {
            return new ServiceResult<>(false,"Not valid subway line!");
        }
        SubwayStation subwayStation = subwayStationRepository.findOne(houseForm.getSubwayStationId());
        if(subwayStation == null || subway.getId() != subwayStation.getSubwayId()){
            return new ServiceResult<>(false,"Not valid subway station line!");
        }
        houseDetail.setSubwayLineId(subway.getId());
        houseDetail.setSubwayLineName(subway.getName());

        houseDetail.setSubwayStationId(subwayStation.getId());
        houseDetail.setSubwayStationName(subwayStation.getName());

        houseDetail.setDescription(houseForm.getDescription());
        houseDetail.setDetailAddress("测试地址");
        houseDetail.setLayoutDesc(houseForm.getLayoutDesc());
        houseDetail.setRentWay(houseForm.getRentWay());

        houseDetail.setRoundService(houseForm.getRoundService());
        houseDetail.setTraffic(houseForm.getTraffic());
        return null;
    }

    /**
     * 用户预定的房源
     * @param status
     * @param start
     * @param size
     * @return
     */
    @Override
    public ServiceMultiResult<Pair<HouseDTO, HouseSubscribeDTO>> querySubscribeList(
            HouseSubscribeStatus status,
            int start,
            int size) {
        //用户登录id
        Long userId = LoginUserUtil.getLoginUserId();
        Pageable pageable = new PageRequest(start / size, size, new Sort(Sort.Direction.DESC, "createTime"));

        Page<HouseSubscribe> page = subscribeRespository.findAllByUserIdAndStatus(userId, status.getValue(), pageable);
        List<Pair<HouseDTO,HouseSubscribeDTO>> result = new ArrayList<>();
        if (page.getSize() < 1) {
            return new ServiceMultiResult<Pair<HouseDTO, HouseSubscribeDTO>>(page.getTotalElements(),result);
        }

        return wrapper(page);
    }
    private ServiceMultiResult<Pair<HouseDTO, HouseSubscribeDTO>> wrapper(Page<HouseSubscribe> page) {
        List<Pair<HouseDTO, HouseSubscribeDTO>> result = new ArrayList<>();

        if (page.getSize() < 1) {
            return new ServiceMultiResult<>(page.getTotalElements(), result);
        }

        List<HouseSubscribeDTO> subscribeDTOS = new ArrayList<>();
        List<Long> houseIds = new ArrayList<>();
        page.forEach(houseSubscribe -> {
            subscribeDTOS.add(modelMapper.map(houseSubscribe, HouseSubscribeDTO.class));
            houseIds.add(houseSubscribe.getHouseId());
        });

        Map<Long, HouseDTO> idToHouseMap = new HashMap<>();
        Iterable<House> houses = houseReposity.findAll(houseIds);
        houses.forEach(house -> {
            idToHouseMap.put(house.getId(), modelMapper.map(house, HouseDTO.class));
        });

        for (HouseSubscribeDTO subscribeDTO : subscribeDTOS) {
            Pair<HouseDTO, HouseSubscribeDTO> pair = Pair.of(idToHouseMap.get(subscribeDTO.getHouseId()), subscribeDTO);
            result.add(pair);
        }

        return new ServiceMultiResult<>(page.getTotalElements(), result);
    }


}
