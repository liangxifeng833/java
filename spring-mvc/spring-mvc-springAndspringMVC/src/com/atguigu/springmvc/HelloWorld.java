package com.atguigu.springmvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 测试helloworld处理器
 * @author lxf
 */
@Controller
public class HelloWorld {
    //引用Spring IOC容器中的bean
    @Autowired
    private UserService userService;
    public  HelloWorld()
    {
        System.out.println("HelloWorld contructor ... ");
    }
    @RequestMapping("/hello")
    public String hello()
    {
        System.out.println("Spring IOC userService = " + userService);
        System.out.println("hello world");
        return "success";
    }
}
