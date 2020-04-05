package domain.service.interfaces.exhibition;

import domain.domain.Exhibition;
import domain.service.genericity.GetListServiceApi;
import org.springframework.stereotype.Service;


/**
 * 查询多条展位主表记录
 * @author : ningyachao
 * @date : 2018-2-07
 */
@Service
public interface GetListExhibitionService extends GetListServiceApi<Exhibition> {

}
