package domain.service.impl.estate;

import domain.dao.impl.EstateDao;
import domain.domain.DomainResponse;
import domain.service.interfaces.estate.GetListEstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询多条物业资源逻辑实现类
 * @author LiangXiFeng 2018-02-02
 */
@Service
public class GetListEstateServiceImpl implements GetListEstateService {
    @Autowired
    private EstateDao dao;

    /**
     * 查询多条记录
     * @param params
     * @return code说明
     *          0 数据库无数据
     *          1 查询成功
     */
    @Override
    public DomainResponse getList(Map params) {
        //是否查询总条数标识
        String isCount = (String) params.get("isCount");
        //总条数
        Integer number = 0 ;
        //初始化返回句柄
        Map result = new HashMap();
        if(isCount !=null && isCount.equals("1"))
        {
            number = dao.getCount(params);
            //查询总记录条数
            result.put("totalNum",number);
            if(number == null || number == 0)
                return new DomainResponse<Object>(0,"数据库无数据","");
        }
        //查询总记录
        List res = dao.getList(params);
        if( res == null || res.size() == 0)
            return new DomainResponse<Object>(0,"数据库无数据","");
        else{
            result.put("list",res);
            return new DomainResponse<Map>(1,"查询成功",result);
        }
    }
}
