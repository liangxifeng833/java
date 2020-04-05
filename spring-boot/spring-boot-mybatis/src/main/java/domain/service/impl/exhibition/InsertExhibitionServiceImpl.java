package domain.service.impl.exhibition;


import domain.dao.impl.ExhibitionDao;
import domain.domain.DomainResponse;
import domain.domain.Exhibition;
import domain.service.interfaces.exhibition.InsertExhibitionService;
import domain.util.YmlConfig;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 展位主表新增记录
 * @author ningyachao 2018-02-02
 */
@Service
public class InsertExhibitionServiceImpl implements InsertExhibitionService {
    @Autowired
    private ExhibitionDao dao;
    @Autowired
    private YmlConfig config;


    /**
     * 新增记录
     * @param entity 实体对象
     * @return code说明
     *      0 新增失败
     *      1 新增成功
     *      2 展位号不能为空
     *      3 展位号格式错误
     *      4 展位号数量已达最大值,不允许添加
     *      5 展位号重复
     */
    @Override
    public DomainResponse<?> insert(Exhibition entity) {
        //验证参数
        DomainResponse checkRes = this.checkParams(entity);
        if (checkRes.getCode() == 1)
        {
            //验证通过
            entity.setState(0);     //添加默认状态
            Integer res =  dao.insert(entity);
            if( res == 1 ){
                String name = entity.getExhibitionName();
                return new DomainResponse<String>(1,"新增成功",name);
            }else {
                return new DomainResponse<String>(0,"新增失败","");
            }
        }else{
            return checkRes;
        }
    }

    /**
     * 验证规则
     * @param entity
     * @return DomainResponse
     */
    private DomainResponse checkParams(Exhibition entity)
    {
        //获取展位号并对其进行格式验证
        String name = entity.getExhibitionName();
        if (name == null)
        {
            return new DomainResponse<String>(2,"展位号不能为空","");
        }else{
            // 按指定模式在字符串查找
            String pattern = "^[A-Z]{1}-[0-9]{3}-[0-9]{3}$";
            // 创建 Pattern 对象
            Pattern r = Pattern.compile(pattern);
            // 现在创建 matcher 对象
            Matcher m = r.matcher(name);
            if (!m.matches()) {
                return new DomainResponse<String>(3,"展位号格式错误","");
            }
        }
        //判断数据总数是否超过totalNum
        int number = dao.getCount(null);
        if(number >= config.getTotalNumber())
            return new DomainResponse<String>(4,"展位号数量已达最大值,不允许添加","");
        //判断展位号是否重复
        Exhibition res = dao.getOne(name);
        if(res != null)
            return new DomainResponse<String>(5,"展位号重复","");
        return new DomainResponse<String>(1,"验证通过","");
    }


}
