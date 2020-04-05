package domain.service.impl.area;

import domain.dao.impl.AreaDao;
import domain.dao.impl.AreaHistoryDao;
import domain.domain.Area;
import domain.domain.AreaHistory;
import domain.domain.DomainResponse;
import domain.service.interfaces.area.InsertAreaService;
import domain.util.CommonCheck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.Timestamp;
import java.util.*;

/**
 * 新增区域逻辑实现类
 * @author LiangXiFeng 2018-02-02
 */
@Service
@Slf4j
public class InsertAreaServiceImpl implements InsertAreaService {
    //区域dao
    @Autowired
    private AreaDao dao;

    //区域历史dao
    @Autowired
    private AreaHistoryDao areaHistoryDao;

    /**
     * 新增
     * @param entity 实体对象
     * @return code说明
     *      0  区域名称不可为空
     *      1  新增成功
     *      2  区域名称不可超过10个字
     *      3  区域名称不可重复
     *      10 新增失败
     *      11 新增区域成功, 但是新增区域历史失败
     */
    //propagation 传播行为 isolation 隔离级别  rollbackFor 回滚规则
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    @Override
    public DomainResponse<?> insert(Area entity) {
        //验证参数
        DomainResponse checkParam = this.checkParam(entity);
        if( checkParam != null ) return checkParam;

        //去首位空格
        entity.setAreaName(entity.getAreaName().trim());
        //新增操作
        int res =  dao.insert(entity);
        if( res == 1 ){
            log.info("=====区域表新增成功~~,id = " + entity.getId());
            //新增区域历史记录表
            if( this.addAreaHistroy(entity) ){
                return new DomainResponse(1,"新增成功",entity.getId());
            }else {
                //手动回滚事务
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return new DomainResponse(11,"新增区域成功, 但是新增区域历史失败",0);
            }
        }else {
            return new DomainResponse(10,"新增失败",0);
        }
    }

    /**
     * 新增区域历史记录表
     * @return
     */
    public Boolean addAreaHistroy(Area entity){

        //新增历史记录
        AreaHistory areaHistory = new AreaHistory();
        areaHistory.setAreaId(entity.getId());
        areaHistory.setField("area_name");
        areaHistory.setValue(entity.getAreaName());
        areaHistory.setValidStart();

        //截止时间当前时间+100年
        Calendar calendar = new GregorianCalendar(2118, 12, 30,0,0,0);
        Date date = calendar.getTime();
        Timestamp timestamp = new Timestamp(date.getTime());
        areaHistory.setValidCutoff(timestamp);
        return false;
        //int insertHistory = areaHistoryDao.insert(areaHistory);

        //return insertHistory == 1 ? true : false;
    }

    /**
     * 验证参数
     * @param entity
     * @return code 说明:
     *      0 区域名称不可为空f
     *      2 区域名称不可超过10个字
     *      3 区域名称不可重复
     */
    public DomainResponse checkParam(Area entity){
        //区域名称
        if( entity.getAreaName() == null || entity.getAreaName().equals("") ){
            return new DomainResponse(0,"区域名称不可为空",0);
        }else {
            String name = entity.getAreaName().trim();
            //区域名称 <= 10
            Integer checkPerson = CommonCheck.checkNameBase( name,10);
            if( checkPerson == 2 )
                return new DomainResponse(2,"区域名称不可超过10个字",0);

            //验证重复
            Map whereMap = new HashMap();
            whereMap.put("area_name", name);
            Map paramMap = new HashMap();
            paramMap.put("equals",whereMap);

            Map whereNoEq = new HashMap();
            //如何是修改要排除本身
            if( entity.getId() != 0 ) {
                Map whereIdMap = new HashMap();
                whereIdMap.put("id",entity.getId());

                paramMap.put("noEquals",whereIdMap);
            }
            //查询区域表 where area_name = name and id != id
            List areaList = dao.getList(paramMap);
            log.info("areaList ===== "+areaList);
            if(  areaList.size() > 0 )
                return new DomainResponse(3,"区域名称不可重复",0);
        }
        return null;
    }

}
