package domain.service.impl.exhibition;


import com.sun.org.apache.xpath.internal.SourceTree;
import domain.dao.impl.ExhibitionDao;
import domain.domain.DomainResponse;
import domain.domain.Exhibition;
import domain.service.interfaces.exhibition.InsertExhibitionService;
import domain.service.interfaces.exhibition.UpdateExhibitionService;
import domain.util.YmlConfig;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 展位主表修改记录
 * @author ningyachao 2018-02-02
 */
@Service
public class UpdateExhibitionServiceImpl implements UpdateExhibitionService {
    @Autowired
    private ExhibitionDao dao;


    /**
     * 修改记录
     * @param entity 实体对象
     * @return code说明
     *      0 修改失败
     *      1 修改成功
     *      2 获取展位号失败
     *      3 展位号格式错误
     */
    @Override
    public DomainResponse<?> update(Exhibition entity) {
        //获取展位号并对其进行格式验证
        String name = entity.getExhibitionName();
        if (name == null)
        {
            return new DomainResponse<String>(2,"获取展位号失败","");
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
        //验证通过修改数据
        Integer result = dao.update(entity);
        if (result == 1)
            return new DomainResponse<String>(1,"修改成功","");
        else
            return new DomainResponse<String>(0,"修改失败","");
    }


}
