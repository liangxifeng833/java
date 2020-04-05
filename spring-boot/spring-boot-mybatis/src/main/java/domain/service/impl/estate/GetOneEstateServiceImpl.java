package domain.service.impl.estate;

import domain.dao.impl.EstateDao;
import domain.domain.DomainResponse;
import domain.domain.Estate;
import domain.service.interfaces.estate.GetOneEstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 查询单条物业资源表
 * @author  liangxifeng 2018-02-02
 */
@Service
public class GetOneEstateServiceImpl implements GetOneEstateService {
    @Autowired
    private EstateDao dao;

    /**
     * 查询service
     * @param id 主键
     * @return code说明
     *          0 数据库无数据
     *          1 查询成功
     *          2 获取主键失败
     */
    @Override
    public DomainResponse getOne(Long id) {
        if(id != null && id != 0)
        {
            Estate res = dao.getOne(id);
            if(res==null)
                return new DomainResponse<String>(0,"数据库无数据","");
            else
                return new DomainResponse<Estate>(1,"查询成功",res);
        }
        return new DomainResponse<Integer>(2,"获取主键失败",0);
    }
}
