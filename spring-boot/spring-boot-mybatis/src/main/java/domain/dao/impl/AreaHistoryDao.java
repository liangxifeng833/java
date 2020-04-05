package domain.dao.impl;

import domain.dao.DaoApi;
import domain.domain.AreaHistory;
import org.springframework.stereotype.Component;

/**
 * 区域历史 DAO 接口类
 * @author  LiangXiFeng 2018.02.01
 */
@Component
public interface AreaHistoryDao extends DaoApi<AreaHistory> {
    /**
     * 通过关联区域表主键删除区域历史记录
     * @param areaId
     * @return 删除后的影响行数
     */
    int deleteByAreaId(Long areaId);
}
