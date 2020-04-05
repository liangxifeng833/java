package domain.service.impl.exhibition_area;

import domain.dao.impl.ExhibitionAreaDao;
import domain.dao.impl.ExhibitionDao;
import domain.domain.DomainResponse;
import domain.domain.Exhibition;
import domain.domain.ExhibitionArea;
import domain.service.interfaces.exhibition.GetOneExhibitionService;
import domain.service.interfaces.exhibition_area.GetOneExhibitionAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 查询展位与区域关联信息表
 * @author  ningyachao 2018-02-02
 */
@Service
public class GetOneExhibitionAreaServiceImpl implements GetOneExhibitionAreaService {
    @Autowired
    private ExhibitionAreaDao dao;

    /**
     * 查询service
     * @param id 主键
     * @return code说明
     *          0 数据库无数据
     *          1 查询成功
     *          2 获取主键失败
     */
    @Override
    public DomainResponse getOne(Long id) {
        if(id != null && id != 0)
        {
            ExhibitionArea res = dao.getOne(id);
            if(res==null)
                return new DomainResponse<String>(0,"数据库无数据","");
            else
                return new DomainResponse<ExhibitionArea>(1,"查询成功",res);
        }
        return new DomainResponse<Integer>(2,"获取主键失败",0);
    }
}
