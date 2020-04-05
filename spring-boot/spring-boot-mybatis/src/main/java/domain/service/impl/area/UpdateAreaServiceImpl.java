package domain.service.impl.area;

import domain.dao.impl.AreaDao;
import domain.dao.impl.AreaHistoryDao;
import domain.domain.Area;
import domain.domain.AreaHistory;
import domain.domain.DomainResponse;
import domain.service.interfaces.area.InsertAreaService;
import domain.service.interfaces.area.UpdateAreaService;
import domain.util.CommonCheck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 修改区域逻辑实现类
 * @author LiangXiFeng 2018-02-02
 */
@Service
@Slf4j
public class UpdateAreaServiceImpl implements UpdateAreaService {
    //区域dao
    @Autowired
    private AreaDao dao;

    //区域历史dao
    @Autowired
    private AreaHistoryDao areaHistoryDao;

    //修改area service实现类
    @Autowired
    private InsertAreaServiceImpl insertArea;

    /**
     * 修改
     * @param entity 实体对象
     * @return code说明
     *      0 区域名称不可为空
     *      1 修改成功
     *      2 区域名称不可超过10个字
     *      3 区域名称不可重复
     *      10 修改区域失败
     *      11 新增区域成功, 但是新增区域历史失败
     *      12 修改主键不可为空
     *      13 修改记录不存在
     */
    //propagation 传播行为 isolation 隔离级别  rollbackFor 回滚规则
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    @Override
    public DomainResponse<?> update(Area entity) {
        //验证主键
        if(entity.getId() == 0)
            return new DomainResponse(12,"修改主键不可为空",entity.getId());

        //验证参数
        DomainResponse checkParam = this.insertArea.checkParam(entity);
        if( checkParam != null ) return checkParam;
        String name = entity.getAreaName().trim();

        //根据主键查询区域信息
        Area area = dao.getOne(entity.getId());
        if( area == null )
            return new DomainResponse(13,"修改记录不存在",entity.getId());

        //判断是否是真正修改, 如果名称相同不执行修改操作
        if(area.getAreaName().equals(name))
            return new DomainResponse(1,"修改成功",entity.getId());

        //去首位空格
        entity.setAreaName(name);
        //修改操作
        int res =  dao.update(entity);
        if( res == 1 ){
            log.info("====修改区域成功===");
            //修改历史版本截止时间为当前时间
            DomainResponse updateHistory =  this.updateHistory(entity);
            if( updateHistory!= null ) return updateHistory;
            log.info("=====修改历史数据截止时间成功====");

            //新增区域历史记录表
            if( insertArea.addAreaHistroy(entity) ){
                return new DomainResponse(1,"修改成功",0);
            }else {
                return new DomainResponse(11,"修改区域成功, 但是新增区域历史失败",entity.getId());
            }
        }else {
            return new DomainResponse(10,"修改区域失败",0);
        }
    }

    /**
     * 修改历史, 原历史更新截止时间 为当前时间
     * @param entity
     * @return
     */
    private DomainResponse updateHistory(Area entity){
        //获取当前时间
        Date nowDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String compares = sdf.format(nowDate);
        log.info("======当前时间="+compares);

        //修改原历史的截止时间
        Map whereMap = new HashMap();
        whereMap.put("key","valid_cutoff");
        whereMap.put("compare",">");
        whereMap.put("value",compares);

        List list = new ArrayList();
        list.add(whereMap);

        Map paramMap = new HashMap();
        paramMap.put("limiting",1);
        paramMap.put("field","id");
        paramMap.put("symbol",list);

        //查询历史记录 select id from area_history where valid_cutoff > 当前时间 limit 1
        List areaList = areaHistoryDao.getList(paramMap);
        if(areaList != null && areaList.size() > 0){
            AreaHistory  history = (AreaHistory) areaList.get(0);
            //获取历史表主键
            long historyId = history.getId();
            //修改截止时间为当前时间
            history.setValidCutoff();
            //修改操作
            int res = areaHistoryDao.update(history);
            if(res != 1)
                return new DomainResponse(20,"区域历史表更新失败",0);
        }else {
            return new DomainResponse(21,"区域历史表无数据",0);
        }
        return null;
    }


}
