package domain;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Spring Boot 应用启动类
 *
 * @author : liangxifeng
 * @date : 2018-1-19
 */
// Spring Boot 应用的标识
@SpringBootApplication
//如果mybatis中service实现类中加入事务注解，需要此处添加该注解
//@EnableTransactionManagement
// mapper 接口类扫描包配置
@MapperScan("domain.dao")
public class Application extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
