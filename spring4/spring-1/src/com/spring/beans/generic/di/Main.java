package com.spring.beans.generic.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * 泛型依赖注入练习
 * @author lxf
 *
 */
public class Main {
    public static void main(String[] args) {
        //创建spring的IOC容器对象
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-generic-di.xml");   
        UserService userService = (UserService)ctx.getBean("userService");
        userService.add();
    }
}
