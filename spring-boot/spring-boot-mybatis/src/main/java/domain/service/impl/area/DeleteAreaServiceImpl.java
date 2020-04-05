package domain.service.impl.area;

import domain.dao.impl.AreaDao;
import domain.dao.impl.AreaHistoryDao;
import domain.domain.Area;
import domain.domain.DomainResponse;
import domain.service.interfaces.area.DeleteAreaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



/**
 * 删除区域逻辑实现类
 * @author liangxifeng 2018-02-07
 */
@Service
@Slf4j
public class DeleteAreaServiceImpl implements DeleteAreaService {
    //区域dao
    @Autowired
    private AreaDao dao;
    //区域历史记录dao
    @Autowired
    private AreaHistoryDao areaHistoryDao;


    /**
     * 删除记录
     * @param id 实体对象主键
     * @return code说明
     *      0 删除区域异常
     *      1 删除成功
     *      2 获取主键失败
     *      3 该记录在数据库中不存在
     *
     *      5 删除区域成功, 但是删除关联历史记录失败
     */
    //propagation 传播行为 isolation 隔离级别  rollbackFor 回滚规则
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    @Override
    public DomainResponse<?> delete(Long id) {
        if(id != null && id != 0 )
        {
            //根据主键查询区域信息
            Area area = dao.getOne(id);
            if(area == null) return new DomainResponse<Integer>(3, "该记录在数据库中不存在", 0);


            //删除操作
            int res = dao.delete(id);
            if(res == 1 ) {
                //删除区域历史记录
                int delHistroy = areaHistoryDao.deleteByAreaId(id);
                if( delHistroy >= 0){
                    log.info("成功删除区域历史记录总数 = "+delHistroy);
                    return new DomainResponse<Integer>(1, "删除成功", res);
                }else {
                    return new DomainResponse<Integer>(5, "删除区域成功, 但是删除关联历史记录失败!", res);
                }
            }
            else
                return new DomainResponse<Integer>(0,"删除区域异常",0);
        }
        return new DomainResponse<Integer>(2,"获取主键失败",0);
    }

}
