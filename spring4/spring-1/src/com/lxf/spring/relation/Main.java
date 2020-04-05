package com.lxf.spring.relation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lxf.spring.autowire.Address;
import com.lxf.spring.autowire.Person;

public class Main {
    public static void main(String[] args)
    {
        //１．创建spring的IOC容器对象
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-relation.xml");   
        Address address1 = (Address)ctx.getBean("address2");
        System.out.println(address1);
        
        Person person = (Person)ctx.getBean("person");
        System.out.println(person);

    }
}
