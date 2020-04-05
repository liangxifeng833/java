package com.lxf.spring.properties;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lxf.spring.autowire.Address;
import com.lxf.spring.autowire.Car;
import com.lxf.spring.autowire.Person;

public class Main {
    public static void main(String[] args) throws SQLException
    {
        //１．创建spring的IOC容器对象
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-properties.xml");   
        //获取数据源
        DataSource dataSource = (DataSource)ctx.getBean("dataSource");
        //打印数据库连接
        System.out.println(dataSource.getConnection());
    }
}
