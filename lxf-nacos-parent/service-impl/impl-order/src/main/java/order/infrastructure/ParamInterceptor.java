package order.infrastructure;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import order.infrastructure.util.TransitionUtil;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;

/**
 * mybatis　参数处理拦截器，
 * 目的是：为了处理mybatis参数类型为具体的实体类，然后将实体对象的字符串类类型换号为latin1编码
 * 解决mysql数据为latin1编码问题
 * 注意：该处理方式要求连接mysql的jdbc的url必须是：characterEncoding=latin1
 * @author liangxifeng
 * @date 2022-01-10
 */
@Intercepts({
        @Signature(type = ParameterHandler.class, method = "setParameters", args = PreparedStatement.class),
        //@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
@Component
@Slf4j
public class ParamInterceptor implements Interceptor {
    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    private static final ReflectorFactory REFLECTOR_FACTORY = new DefaultReflectorFactory();

    @Autowired
    private Environment environment;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //读取配置文件中的开关
        String isEnable = environment.getProperty("ljconfig.mybatis.interceptor.param-enable");
        //如果该拦截器关闭，则直接返回,不做任何处理
        if(isEnable.trim().equals("false")) {
            log.info("mybatis参数拦截器开关是关闭状态.....");
            return invocation.proceed();
        }
        // 获取拦截器拦截的设置参数对象DefaultParameterHandler
        ParameterHandler parameterHandler = (ParameterHandler) invocation.getTarget();
        // 通过mybatis的反射来获取对应的值
        MetaObject metaResultSetHandler = MetaObject.forObject(parameterHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, REFLECTOR_FACTORY);
        //MappedStatement mappedStatement = (MappedStatement) metaResultSetHandler.getValue("mappedStatement");
        //得到参数PO的实体对象
        Object parameterObject = metaResultSetHandler.getValue("parameterObject");
        log.info("mybatis参数拦截器，拦截到的参数类型是="+parameterObject.getClass().getName()+",参数值="+parameterObject);
        //如果参数类型为整数，map 或者　collection类型，则不需要处理参数
        if(parameterObject instanceof Number
                || parameterObject instanceof Map
                || parameterObject instanceof Collection
                || parameterObject instanceof String){
            return invocation.proceed();
        }

        //反射获取参数对象
        //MetaObject param = MetaObject.forObject(parameterObject, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, REFLECTOR_FACTORY);

        //获取该PO的具体类型
        Class<?> aClass = parameterObject.getClass();
        //bean 准话为　map
        Map<String, Object> map = BeanUtil.beanToMap(parameterObject);
        StringBuffer convertCodeStr = new StringBuffer("已转换参数的值：");
        for (String s : map.keySet()) {
            //判断需要转换的值是字符串类型，且是utf8类型,则需要将utf8转换为latin1c
            if(map.get(s) != null && map.get(s) instanceof String) {
                String latin1Str = String.valueOf(map.get(s));
                //如果该字符串是utf8类型数据，则转码为latin1
                if (TransitionUtil.isUTF8(latin1Str)) {
                    map.put(s, TransitionUtil.utf8ToLatin1(latin1Str));
                    convertCodeStr.append(s+":"+map.get(s)+";");
                }
            }
        }
        log.info(convertCodeStr.toString());
        //将map转回具体的PO，然后组装为list
        Object poNew = JSON.parseObject(JSON.toJSONString(map), aClass);
        //回写parameterObject对象
        metaResultSetHandler.setValue("parameterObject", poNew);
        return invocation.proceed();
    }

    /**
     * 生成拦截对象的代理
     * @param target 目标对象
     * @return 代理对象
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }


    /**
     * mybatis配置的属性
     * @param properties mybatis配置的属性
     */
    @Override
    public void setProperties(Properties properties) {

    }
}
