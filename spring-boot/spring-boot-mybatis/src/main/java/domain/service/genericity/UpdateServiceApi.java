package domain.service.genericity;

import domain.domain.DomainResponse;

/**
 * 修改Service泛型接口
 * @author: lxf
 * @date: 2018-1-19
 * @param <T>
 */

public interface UpdateServiceApi<T> {

    /**
     * 修改数据
     * @param entity 实体对象
     * @return 
     */
    DomainResponse<?> update(T entity);
}
