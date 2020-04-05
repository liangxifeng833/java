package domain.service.impl.estate;

import domain.dao.impl.EstateDao;
import domain.domain.DomainResponse;
import domain.domain.Estate;
import domain.service.interfaces.estate.InsertEstateService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 新增物业资源实现类
 * @author LiangXiFeng 2018-02-02
 */
@Service
@Slf4j
public class InsertEstateServiceImpl implements InsertEstateService {
    //物业资源dao
    @Autowired
    private EstateDao dao;


    /**
     * 新增
     * @param entity 实体对象
     * @return code说明
     *      0 楼层不可为空
     *      1 新增成功
     *      2 面积不可为空
     *      3 面积必须>0,并且最大面积不可超过9999.99
     *      4 坐标不可为空
     *      5 坐标点最少3个
     *      6 每个坐标点必须包含两个元素
     *      7 年份不可为空
     *      10 新增失败
     */
    //propagation 传播行为 isolation 隔离级别  rollbackFor 回滚规则
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    @Override
    public DomainResponse<?> insert(Estate entity) {
        //验证参数
        DomainResponse checkParam = this.checkParam(entity);
        if( checkParam != null ) return checkParam;
        int res =  dao.insert(entity);
        if( res == 1 ){
            return new DomainResponse(1,"新增成功",entity.getId());
        }else {
            return new DomainResponse(10,"新增失败",0);
        }
    }


    /**
     * 验证参数
     * @param entity
     * @return code 说明:
     *      0 楼层不可为空
     *      2 面积不可为空
     *      3 面积必须>0,并且最大面积不可超过9999.99
     *      4 坐标不可为空
     *      5 坐标点最少3个
     *      6 每个坐标点必须包含两个元素
     *      7 年份不可为空
     */
    public DomainResponse checkParam(Estate entity){
        //验证楼层
        if( entity.getEstateFloor() == 0 )
            return new DomainResponse(0,"楼层不可为空",0);

        //验证使用面积
        if( entity.getEstateProportion() == 0 ){
            return new DomainResponse(2,"面积不可为空",0);
        }else if( entity.getEstateProportion() > 9999.99 || entity.getEstateProportion() < 0  ){
            return new DomainResponse(3,"面积必须>0,并且最大面积不可超过9999.99",0);
        }

        //验证坐标
        log.info("参数传过来的坐标 = "+entity.getEstateMap());
        if( entity.getEstateMap() == null || entity.getEstateMap().equals(""))
            return new DomainResponse(4,"坐标不可为空",0);
        //将左边转换为json数组
        JSONArray mapArray = JSONArray.fromObject(entity.getEstateMap());
        log.info("坐标个数="+mapArray.size());
        //获取坐标个数
        int mapSize = mapArray.size();
        if( mapSize < 3 )
            return new DomainResponse(5,"坐标点最少3个",0);
        for ( int i=0; i<mapSize; i++ ) {
            //获取坐标点
            List point = (List)mapArray.get(i);
            if(point.size() != 2)
                return new DomainResponse(6,"每个坐标点必须包含两个元素",0);
        }
        //修改操作不验年份
        if( entity.getId() == 0 ){
            log.info("新增操作entity.id == "+entity.getId());
            //验证年份
            if( entity.getEstateYear() == null || entity.getEstateYear().equals("") )
                return new DomainResponse(7 ,"年份不可为空",0);
        }
        return null;
    }

}
