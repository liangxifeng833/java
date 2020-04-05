package com.lxf.spring.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args)
    {
        //创建spring的IOC容器对象
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans-factory.xml");   
        Car car1 = (Car)ctx.getBean("car1");
        System.out.println("使用Bean： "+car1);
        
        Car car2 = (Car)ctx.getBean("car2");
        System.out.println("使用Bean： "+car2);
        //关闭IOC容器
        ctx.close();
    }
}
