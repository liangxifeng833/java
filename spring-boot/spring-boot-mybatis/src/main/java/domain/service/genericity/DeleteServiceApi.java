package domain.service.genericity;

import domain.domain.DomainResponse;

/**
 * 删除Service泛型接口
 * @author: lxf
 * @date: 2018-1-19
 * @param <T>
 */

public interface DeleteServiceApi<T> {

    /**
     * 删除数据
     * @param id 主键
     * @return 
     */
    DomainResponse<?> delete(Long id);
}
