package domain.service.interfaces.exhibition_area;

import domain.domain.ExhibitionArea;
import domain.service.genericity.GetListServiceApi;
import org.springframework.stereotype.Service;



/**
 * 查询多条展位与区域关系表记录
 * @author : ningyachao
 * @date : 2018-2-07
 */
@Service
public interface GetListExhibitionAreaService extends GetListServiceApi<ExhibitionArea> {

}
