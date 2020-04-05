package com.atguigu.springmvc;

import org.springframework.stereotype.Service;
/**
 * 初始化在Spring的IOC容器中
 * @author lxf
 */
@Service
public class UserService {
    public  UserService()
    {
        System.out.println("UserService Contructor...");
    }
}
