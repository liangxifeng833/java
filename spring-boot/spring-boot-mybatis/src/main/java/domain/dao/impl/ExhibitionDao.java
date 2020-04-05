package domain.dao.impl;

import domain.dao.DaoApi;
import domain.domain.Exhibition;
import org.springframework.stereotype.Component;

/**
 * 展位主表 DAO 接口类
 * @author  ningyachao 2018.02.07
 */
@Component
public interface ExhibitionDao extends DaoApi<Exhibition> {
    /**
     * 通过主键查询一条记录
     * @param String
     * @return 返回查询实体
     */
    Exhibition getOne(String name);
}
