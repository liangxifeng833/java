package com.lxf;

import com.lxf.bean.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        //查询类路径 加载配置文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
        //根据id获取bean
        //Spring就是一个大工厂（容器）专门生成bean bean就是对象
        Person person = (Person) applicationContext.getBean("Person");
        //输出获取到的对象
        System.out.println("person = " + person);
    }
}
