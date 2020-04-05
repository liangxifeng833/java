package domain.service.genericity;

import domain.domain.DomainResponse;

/**
 * 根据唯一条件查询单条记录Service泛型接口
 * @author: lxf
 * @date: 2018-1-19
 * @param <T>
 */

public interface GetOneServiceApi<T> {

    /**
     * 查询单条
     * @param id 主键
     * @return 
     */
    DomainResponse<?> getOne(Long id);
}
