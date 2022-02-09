package order.infrastructure;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.handlers.AbstractSqlParserHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import order.infrastructure.util.TransitionUtil;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * mybatis 拦截器，负责处理从数据库中差出来数据，如果是字符串类型，则转码为utf8编码数据
 * 该阶段，是读取后转换为PO阶段后做的操作
 * @author liangxifeng
 * @date 2022-01-06
 */
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})}
        )
@Slf4j
@Component
public class MybatisInterceptor implements Interceptor {
    @Autowired
    private Environment environment;
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //读取配置文件中的开关
        String isEnable = environment.getProperty("ljconfig.mybatis.interceptor.query-enable");
        //如果该拦截器关闭，则直接返回,不做任何处理
        if(isEnable.trim().equals("false")) {
            log.info("mybatis查询query拦截器开关是关闭状态.....");
            return invocation.proceed();
        }
        //执行请求方法，并将所得结果保存到result中,List中类型为PO
        Object result = invocation.proceed();
        if (result instanceof ArrayList) {
            List list = new ArrayList();
            ArrayList resultList = (ArrayList) result;
            for (int i = 0; i < resultList.size(); i++) {
                //获取单独的PO
                Object object = resultList.get(i);
                //如果是整型，则不参与编码转换
                if (object instanceof Integer || object instanceof Long ) {
                    list.add(object);
                } else {
                    //获取PO 的具体class类型
                    Class<?> aClass = object.getClass();
                    //bean 准为　map
                    Map<String, Object> map = BeanUtil.beanToMap(object);
                    StringBuffer convertCodeStr = new StringBuffer("mybatis拦截器-已转换编码字段的值：");
                    for (String s : map.keySet()) {
                        //判断需要转换的值是字符串类型，且是latin1类型,则需要讲latin1转换为utf8
                        if(map.get(s) != null && map.get(s) instanceof String) {
                            String latin1Str = String.valueOf(map.get(s));
                            //Boolean res = latin1Str.equals(new String(latin1Str.getBytes("ISO-8859-1"),"ISO-8859-1") );
                            //如果该字符串是latin1类型数据，则转码为utf8
                            if (TransitionUtil.isLatin1(latin1Str)) {
                                map.put(s, TransitionUtil.latin1ToUtf8(latin1Str));
                                convertCodeStr.append(s+":"+map.get(s)+";");
                            }
                        }
                    }
                    log.info(convertCodeStr.toString());
                    //将map转回具体的PO，然后组装为list
                    Object poNew = JSON.parseObject(JSON.toJSONString(map), aClass);
                    list.add(poNew);
                }
            }
            return list;
        }
        return result;
    }
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties arg0) {
    }
}
