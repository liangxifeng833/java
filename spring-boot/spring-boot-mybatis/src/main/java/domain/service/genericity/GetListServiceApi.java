package domain.service.genericity;

import domain.domain.DomainResponse;

import java.util.Map;

/**
 * 根据复合条件查询多条记录Service泛型接口
 * @author: lxf
 * @date: 2018-1-19
 * @param <T>
 */

public interface GetListServiceApi<T> {

    /**
     * 根据复合条件查询多条记录
     * @param params
     * @return 
     */
    DomainResponse<?> getList(Map params);
}
