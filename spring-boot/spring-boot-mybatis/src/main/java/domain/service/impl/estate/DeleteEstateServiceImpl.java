package domain.service.impl.estate;

import domain.dao.impl.EstateDao;
import domain.domain.DomainResponse;
import domain.domain.Estate;
import domain.service.interfaces.estate.DeleteEstateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * 删除物业资源逻辑实现类
 * @author liangxifeng 2018-02-07
 */
@Service
@Slf4j
public class DeleteEstateServiceImpl implements DeleteEstateService {
    //区域dao
    @Autowired
    private EstateDao dao;

    /**
     * 删除记录
     * @param id 实体对象主键
     * @return code说明
     *      0 删除异常
     *      1 删除成功
     *      2 获取主键失败
     *      3 该记录在数据库中不存在
     *      4 物业资源未签约状态不可删除
     */
    //propagation 传播行为 isolation 隔离级别  rollbackFor 回滚规则
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    @Override
    public DomainResponse<?> delete(Long id) {
        if(id != null && id != 0 )
        {
            //根据主键查询区域信息
            Estate estate = dao.getOne(id);
            if(estate == null) return new DomainResponse<Integer>(3, "该记录在数据库中不存在", 0);

            //验证未签约状态可以删除
            if(estate.getState() != 0 )
                return new DomainResponse<Integer>(4, "只能删除未签约状态的物业资源", 0);

            //删除操作
            int res = dao.delete(id);
            if(res == 1 ) {
                return new DomainResponse<Integer>(1, "删除成功", res);
            }
            else
                return new DomainResponse<Integer>(0,"删除异常",0);
        }
        return new DomainResponse<Integer>(2,"获取主键失败",0);
    }
}
