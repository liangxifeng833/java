package com.lxf.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;

import com.lxf.entity.Page;


/**
 * 分页拦截器
 * @author lxf
 *
 */
//@Intercepts({@Signature(
//        type= StatementHandler.class,
//        method = "prepare",
//        args = {Connection.class,Integer.class})})
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PageInterceptor implements  Interceptor {
    /**
     * 拦截器执行顺序3.拦截处理操作
     */
    public Object intercept(Invocation invocation) throws Throwable {
        //获取被拦截的对象
        /*
        StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
        
        //判断是否拦截该对象的方法
        MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, (ObjectWrapperFactory) SystemMetaObject.DEFAULT_OBJECT_FACTORY);
        // TODO Auto-generated method stub
        MappedStatement  mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        //获取xml配置文件中sql语句的id
        String id = mappedStatement.getId();
        

        if(id.matches(".+ByInterceptor$")){
            BoundSql bondSql = statementHandler.getBoundSql();
            //获取原始sql语句
            String sql = bondSql.getSql();
            //查询总条数的sql语句
            String countSql = "select count(*) from ("+ sql +") a ";
            Connection connection = (Connection)invocation.getArgs()[0];
            PreparedStatement countStatement = connection.prepareStatement(countSql);
            
            ParameterHandler pH = (ParameterHandler)metaObject.getValue("delegate.parameterHandler");
            pH.setParameters(countStatement);
            ResultSet rs = countStatement.executeQuery();
            
            Map<String,Object> parameter  = (Map<String,Object>)bondSql.getParameterObject();
            Page page = (Page)parameter.get("page");
            if(rs.next())
            {
                page.setTotalNumber(rs.getInt(1));
            }
            //改造后带分页查询的sql语句
            String pageSql = sql + " limit" + page.getDbIndex() + "," + page.getDbNumber();
            metaObject.setValue("delegate.boundSql.sql", pageSql);
        }
        */
        //让原本被拦截的方法继续执行
         
        return invocation.proceed();
    }

    /**
     * 拦截器执行顺序2.拦截器先将拦截的对象target，进行过滤
     * 如果被拦截的对象有@Intercepts注解，并配置了拦截，那么使用动态代理机制生成代理类，
     * 并调用intercept方法处理业务罗辑，否则放行，不经过intercept方法
     */
    public Object plugin(Object target) {
        // TODO Auto-generated method stub
        return Plugin.wrap(target, this);
    }
    
    //拦截器执行顺序１．获取配置文件<plugin>中的<property>属性值
    public void setProperties(Properties properties) {
        // TODO Auto-generated method stub
        properties.getProperty("testName");
    }

}
