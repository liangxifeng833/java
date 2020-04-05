package domain.service.impl.exhibition;

import domain.dao.impl.ExhibitionDao;
import domain.domain.DomainResponse;
import domain.domain.Exhibition;
import domain.service.interfaces.exhibition.GetOneExhibitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 展位主表查询单条记录
 * @author  ningyachao 2018-02-02
 */
@Service
public class GetOneExhibitionServiceImpl implements GetOneExhibitionService {
    @Autowired
    private ExhibitionDao dao;

    /**
     * 查询service
     * @param name 主键
     * @return code说明
     *          0 数据库无数据
     *          1 查询成功
     *          2 获取主键失败
     */
    @Override
    public DomainResponse getOne(String name) {
        if(name != null && !name.equals(""))
        {
            Exhibition res = dao.getOne(name);
            if(res==null) {
                return new DomainResponse<String>(0, "数据库无数据", "");
            }else {
                return new DomainResponse<Exhibition>(1, "查询成功", res);
            }
        }
        return new DomainResponse<Integer>(2,"获取主键失败",0);
    }
    /**
     * 查询service
     * @param id 主键
     * @return code说明
     *          2 该表主键为String类型
     */
    @Override
    public DomainResponse getOne(Long id) {

        return new DomainResponse<Integer>(2,"该表主键为String类型",0);
    }
}
