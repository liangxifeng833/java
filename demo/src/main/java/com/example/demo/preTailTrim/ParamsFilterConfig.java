package com.example.demo.preTailTrim;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;

/**
 * SpringBoot中去除@RequestBody中前后端空格 配置过滤器注册 类似web.xml注册
 * @author liangxifeng
 * @date 2022/7/21 14:34
 */

//@Configuration
public class ParamsFilterConfig {
    //@Bean
    public FilterRegistrationBean paramsFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new ParamsFilter());
        registration.addUrlPatterns("/*");
        registration.setName("paramsFilter");
        registration.setOrder(Integer.MAX_VALUE-1);
        return registration;
    }
}
