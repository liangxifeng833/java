package domain.service.impl.estate;

import domain.dao.impl.EstateDao;
import domain.domain.DomainResponse;
import domain.domain.Estate;
import domain.service.interfaces.estate.UpdateEstateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 修改物业资源逻辑实现类
 * @author LiangXiFeng 2018-02-02
 */
@Service
@Slf4j
public class UpdateEstateServiceImpl implements UpdateEstateService {
    //物业资源dao
    @Autowired
    private EstateDao dao;
    //新增物业资源罗辑实现类
    @Autowired
    private InsertEstateServiceImpl insertEstate;


    /**
     * 修改
     * @param entity 实体对象
     * @return code说明
     *      0 楼层不可为空
     *      1 修改成功
     *      2 面积不可为空
     *      3 面积必须>0,并且最大面积不可超过9999.99
     *      4 坐标不可为空
     *      5 坐标点最少3个
     *      6 每个坐标点必须包含两个元素
     *      10 修改失败
     *      11 修改主键不可为空
     *      12 修改记录不存在
     */
    //propagation 传播行为 isolation 隔离级别  rollbackFor 回滚规则
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    @Override
    public DomainResponse<?> update(Estate entity) {
        //验证主键
        if(entity.getId() == 0)
            return new DomainResponse(11,"修改主键不可为空",entity.getId());

        //验证参数
        DomainResponse checkParam = this.insertEstate.checkParam(entity);
        if( checkParam != null ) return checkParam;

        //根据主键查询物业资源信息
        Estate estate = dao.getOne(entity.getId());
        if( estate == null )
            return new DomainResponse(12,"修改记录不存在",entity.getId());
        //修改不修改年份
        entity.setEstateYear(null);
        //修改操作
        int res =  dao.update(entity);
        if( res == 1 ){
            return new DomainResponse(1,"修改成功",0);
        }else {
            return new DomainResponse(10,"修改失败",0);
        }
    }


}
