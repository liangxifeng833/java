package com.lxf.spring.autowire;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args)
    {
        //１．创建spring的IOC容器对象
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-autowire.xml");   
        //null赋值和级联属性赋值配置测试
        Person p = (Person)ctx.getBean("person");
        System.out.println(p);
    }
}
