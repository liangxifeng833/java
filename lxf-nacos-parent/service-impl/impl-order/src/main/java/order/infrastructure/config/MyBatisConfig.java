package order.infrastructure.config;

import order.infrastructure.MybatisInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * mybatis配置类
 *  这里将自定义的拦截器MybatisInterceptor注册到注入到sqlSessionFactory配置中
 * @author liangxifeng
 * @date 2022-01-09
 */
//@Configuration
public class MyBatisConfig {
    @Autowired
    private List<SqlSessionFactory> sqlSessionFactoryList;

    /**
     * @PostConstruct 依赖注入完成后被自动调用
     */
    //@PostConstruct
    public void addMySqlInterceptor() {
        //自己定义的插件拦截器类，注入到sqlSessionFactory配置中
        MybatisInterceptor interceptor = new MybatisInterceptor();
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            sqlSessionFactory.getConfiguration().addInterceptor(interceptor);
        }
    }
}
