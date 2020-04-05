package com.lxf.spring.scope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lxf.spring.autowire.Address;
import com.lxf.spring.autowire.Car;
import com.lxf.spring.autowire.Person;

public class Main {
    public static void main(String[] args)
    {
        //１．创建spring的IOC容器对象
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-scope.xml");   
        Car car1 = (Car)ctx.getBean("car");
        Car car2 = (Car)ctx.getBean("car");
        System.out.println(car1==car2);
    }
}
