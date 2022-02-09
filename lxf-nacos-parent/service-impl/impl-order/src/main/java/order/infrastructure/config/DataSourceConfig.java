package order.infrastructure.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * 数据源配置
 * @author liangxifeng
 * @date 2022-01-06
 */
//@Configuration
//@MapperScan(basePackages = "order.infrastructure.dao", sqlSessionFactoryRef = "adminSqlSessionFactory")
public class DataSourceConfig {
    @Bean("adminDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource getDb1DataSource(){
        //注意是DruidDataSourceBuilder 而不是DataSourceBuilder，后者的话设置无效
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("adminSqlSessionFactory")
    public SqlSessionFactory db1SqlSessionFactory(@Qualifier("adminDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mappers/*.xml"));
        return bean.getObject();
    }

    @Bean("adminSqlSessionTemplate")
    public SqlSessionTemplate db1SqlSessionTemplate(@Qualifier("adminSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
