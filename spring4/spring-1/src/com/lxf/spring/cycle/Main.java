package com.lxf.spring.cycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args)
    {
        //创建spring的IOC容器对象
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans-cycle.xml");   
        Car car1 = (Car)ctx.getBean("car");
        System.out.println("使用Bean： "+car1);
        //关闭IOC容器
        ctx.close();
    }
}
