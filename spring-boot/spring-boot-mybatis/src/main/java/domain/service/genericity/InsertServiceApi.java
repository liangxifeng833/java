package domain.service.genericity;

import domain.domain.DomainResponse;

/**
 * 新增Service泛型接口
 * @author: lxf
 * @date: 2018-1-19
 * @param <T>
 */

public interface InsertServiceApi<T> {

    /**
     * 新增数据
     * @param entity 实体对象
     * @return
     */
    DomainResponse<?> insert(T entity);
}
