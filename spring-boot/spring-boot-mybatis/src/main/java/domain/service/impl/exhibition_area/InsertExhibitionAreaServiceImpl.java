package domain.service.impl.exhibition_area;


import domain.dao.impl.AreaDao;
import domain.dao.impl.ExhibitionAreaDao;
import domain.dao.impl.ExhibitionDao;
import domain.domain.Area;
import domain.domain.DomainResponse;
import domain.domain.Exhibition;
import domain.domain.ExhibitionArea;
import domain.service.interfaces.exhibition_area.InsertExhibitionAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * 新增展位与区域关系信息逻辑实现类
 * @author ningyachao 2018-02-02
 */
@Service
public class InsertExhibitionAreaServiceImpl implements InsertExhibitionAreaService {
    @Autowired
    private ExhibitionAreaDao exhibitionAreaDao;
    @Autowired
    private ExhibitionDao exhibitionDao;
    @Autowired
    private AreaDao areaDao;


    /**
     * 新增记录
     * @param entity 实体对象
     * @return code说明
     *      0 新增失败
     *      1 新增成功
     *      2 接收必要参数失败
     *      3 展位主表中无记录
     *      4 区域表中无记录
     *      5 该记录在数据库中已经存在
     *
     */
    @Override
    public DomainResponse<?> insert(ExhibitionArea entity) {
        //验证规则
        DomainResponse res = this.checkParams(entity);
        if (res.getCode() == 1) {
            String name = entity.getExhibitionName().trim();
            Long areaId = entity.getAreaId();
            //查询关联表
            Map<String,Object> map = new HashMap<String,Object>();
            Map<String,Object> equals = new HashMap<String,Object>();
            equals.put("exhibition_name", name);
            equals.put("area_id", areaId);
            equals.put("state", 1);
            map.put("equals", equals);
            map.put("limiting", 1);
            List relation = exhibitionAreaDao.getList(map);
            if (relation.size() == 1)
            {
                return new DomainResponse<String>(5,"该记录在数据库中已经存在","");
            }else{
                entity.setState(1);
                //截止时间当前时间+100年
                Calendar calendar = new GregorianCalendar(2118, 12, 30,0,0,0);
                Date date = calendar.getTime();
                entity.setValidCutoff(date);
                Integer number = exhibitionAreaDao.insert(entity);
                if( number == 1 ){
                    Long id = entity.getId();
                    return new DomainResponse<Long>(1,"新增成功",id);
                }else {
                    return new DomainResponse<String>(0,"新增失败","");
                }
            }
        }else{
            return res;
        }
    }

    /**
     * 验证必要规则
     * @param entity
     * @return
     */
    public DomainResponse checkParams(ExhibitionArea entity)
    {
        //获取必要参数
        String name = entity.getExhibitionName();
        Long areaId = entity.getAreaId();
        Date date = entity.getValidStart();
        if (name == null || areaId.equals(0L) || date == null)
            return new DomainResponse<String>(2,"接收必要参数失败","");
        //验证相关记录表中是否有记录
        //查询展位号信息
        Exhibition res = exhibitionDao.getOne(name.trim());
        if (res == null)
            return new DomainResponse<String>(3,"展位主表中无记录","");
        //查询区域信息
        Area area = areaDao.getOne(areaId);
        if (area == null)
            return new DomainResponse<String>(4,"区域表中无记录","");
        return new DomainResponse<String>(1,"验证通过","");
    }

}
