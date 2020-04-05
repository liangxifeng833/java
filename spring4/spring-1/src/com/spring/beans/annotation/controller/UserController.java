package com.spring.beans.annotation.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import com.spring.beans.annotation.TestObject;
import com.spring.beans.annotation.repository.UserRepository;
import com.spring.beans.annotation.service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    
    @Autowired
    //如果IOC容器中存在多个UserRepository接口的实现类，
    //可以指定具体装在哪个实现类，注意首字母小写(spring规定)
    @Qualifier("jdbcRespository")
    private UserRepository userRepository;
    
   //如果IOC容器中没有testObj这个bean,则使用@Autowired(required=false，否则异常
    @Autowired(required=false)    
    private TestObject testObj;
    public void execute()
    {
        System.out.println("UserControler execute...");
        userService.add();
    }
}
