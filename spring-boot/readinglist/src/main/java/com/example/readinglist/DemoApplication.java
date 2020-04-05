package com.example.readinglist;

import groovy.lang.Grab;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Spring Boot 启动类
 */
@SpringBootApplication
//@Grab("spring-boot-starter-remote-shell")
public class DemoApplication extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DemoApplication.class);
    }

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
