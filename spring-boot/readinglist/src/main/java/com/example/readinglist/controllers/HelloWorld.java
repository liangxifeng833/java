package com.example.readinglist.controllers;

import com.example.readinglist.others.CustomerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//使用application.yml中的lxf-customer前缀的自定义属性
@ConfigurationProperties(prefix = "lxf-customer")
public class HelloWorld {
    private final Logger logger = LoggerFactory.getLogger(HelloWorld.class);
    @Autowired
    //自定义属性文件，该文件通过@ConfigurationProperties("lxf-customer")
    //获取application.yml中的lxf-customer.username属性
    private CustomerProperties customerProperties;

    @GetMapping(value = "/hello")
    public String testHello()
    {
        //日志级别从低到高分为TRACE < DEBUG < INFO < WARN < ERROR < FATAL，如果设置为WARN，则低于WARN的信息都不会输出。
        logger.trace("日志输出 trace");
        logger.debug("日志输出 debug");
        logger.info("日志输出 info");
        logger.warn("日志输出 warn");
        logger.error("日志输出 error");
        return "hello world!" + customerProperties.getUsername() ;
    }
}
