package domain.service.impl.area_history;

import domain.dao.impl.AreaDao;
import domain.dao.impl.AreaHistoryDao;
import domain.domain.Area;
import domain.domain.AreaHistory;
import domain.domain.DomainResponse;
import domain.service.interfaces.area.GetOneAreaService;
import domain.service.interfaces.area_history.GetOneAreaHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 查询单条区域历史表
 * @author  liangxifeng 2018-02-02
 */
@Service
public class GetOneAreaHistoryServiceImpl implements GetOneAreaHistoryService {
    @Autowired
    private AreaHistoryDao dao;

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
            AreaHistory res = dao.getOne(id);
            if(res==null)
                return new DomainResponse<String>(0,"数据库无数据","");
            else
                return new DomainResponse<AreaHistory>(1,"查询成功",res);
        }
        return new DomainResponse<Integer>(2,"获取主键失败",0);
    }
}
