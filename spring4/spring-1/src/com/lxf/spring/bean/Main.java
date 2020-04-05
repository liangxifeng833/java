package com.lxf.spring.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args)
    {
        /*
        HelloWorld helloworld = new HelloWorld();
        helloworld.setName("liangxifeng");
        */
        //１．创建spring的IOC容器对象
        //ApplicationContext代表IOC容器
        // ClassPathXmlApplicationContext是ApplicationContext接口的一个实现类，该实现类从类路径下加载配置文件
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        //２．从IOC容器中获取Bean实例
        //利用id定位到IOC容器中的bean
        //HelloWorld helloworld = (HelloWorld)ctx.getBean("helloWorld");
        //利用类型返回IOC容器中的bean，要求IOC容器中必须只能有一个该类型的Bean
        //HelloWorld helloworld = ctx.getBean(HelloWorld.class);
        //helloworld.setName("aaa");
        //３．调用hello方法
       // helloworld.hello();
        
        Car car1 = (Car) ctx.getBean("car1");
        System.out.println(car1);
        Car car2 = (Car) ctx.getBean("car2");
        System.out.println(car2);
        Car car3 = (Car) ctx.getBean("car1-1");
        System.out.println(car3);
        
        //null赋值和级联属性赋值配置测试
        Person p2 = (Person)ctx.getBean("person2");
        System.out.println(p2);
        
        //集合list属性赋值测试
        Person p3 = (Person)ctx.getBean("person3");
        System.out.println(p3);
        
        //集合map属性赋值测试
        Person p4 = (Person)ctx.getBean("person4");
        System.out.println(p4);
        
        //配置properties属性值测试 
        DataSource datasource = (DataSource)ctx.getBean("data-source");
        System.out.println(datasource.getProperties());
        
        //调用单独的list集合属性测试
        Person p5 = (Person)ctx.getBean("person5");
        System.out.println(p5);
        
        //使用p命名空间为bean属性赋值
        Person p6 =  (Person)ctx.getBean("person6");
        System.out.println(p6);
    }
}
