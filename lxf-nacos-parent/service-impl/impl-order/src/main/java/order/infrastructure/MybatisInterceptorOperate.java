package order.infrastructure;


import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.spring.util.BeanUtils;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.handlers.AbstractSqlParserHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import order.infrastructure.util.ReflectUtil;
import order.infrastructure.util.TransitionUtil;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * mybatis 拦截器
 * @author liangxifeng
 * @date 2022-01-06
 */
@Slf4j
@AllArgsConstructor
//@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
//@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}) })
//@Component
public class MybatisInterceptorOperate implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        Object[] args = invocation.getArgs();
        //遍历处理所有参数，update方法有两个参数，参见Executor类中的update()方法。
        for(int i=0;i<args.length;i++)
        {
            Object arg=args[i];
            String className=arg.getClass().getName();
            System.out.println(i + " 参数类型："+className);
            //第一个参数处理。根据它判断是否给“操作属性”赋值。
            if(arg instanceof MappedStatement)
            {
                MappedStatement ms = (MappedStatement)arg;
                SqlCommandType sqlCommandType = ms.getSqlCommandType();
                System.out.println("操作类型："+sqlCommandType);
                if(sqlCommandType == SqlCommandType.INSERT || sqlCommandType==SqlCommandType.UPDATE)
                {
                    continue;
                }else {
                    break;
                }
            }

            //第二个参数处理。（只有第二个程序才能跑到这）

        }
        return invocation.proceed();
    }


    /**
     * 生成拦截对象的代理
     *
     * @param target 目标对象
     * @return 代理对象
     */
    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        }
        return target;
    }


    /**
     * mybatis配置的属性
     *
     * @param properties mybatis配置的属性
     */
    @Override
    public void setProperties(Properties properties) {

    }

}
