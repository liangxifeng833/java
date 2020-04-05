package com.spring.beans.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.beans.annotation.controller.UserController;
import com.spring.beans.annotation.repository.UserRepository;
import com.spring.beans.annotation.service.UserService;

public class Main {
    public static void main(String[] args)
    {
        //创建spring的IOC容器对象
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-annotation.xml");   
        
        TestObject testObj = (TestObject)ctx.getBean("testObject");
        System.out.println(testObj);
        
        UserService userService = (UserService)ctx.getBean("userService");
        System.out.println(userService);
        
        UserController userController = (UserController)ctx.getBean("userController");
       System.out.println(userController);
       userController.execute();
        
        UserRepository userResp = (UserRepository)ctx.getBean("userRepository");
        System.out.println(userResp);
    }
}
