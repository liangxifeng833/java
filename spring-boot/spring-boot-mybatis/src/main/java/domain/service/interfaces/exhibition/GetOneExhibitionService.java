package domain.service.interfaces.exhibition;

import domain.domain.DomainResponse;
import domain.domain.Exhibition;
import domain.service.genericity.GetOneServiceApi;

/**
 * 查询单条展位主表记录
 * @author : ningyachao
 * @date : 2018-2-07
 */
public interface GetOneExhibitionService extends GetOneServiceApi<Exhibition> {
    /**
     * 查询单条
     * @param name 主键
     * @return
     */
    DomainResponse<?> getOne(String name);
}
