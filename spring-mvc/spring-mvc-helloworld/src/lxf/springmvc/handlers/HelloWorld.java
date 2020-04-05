package lxf.springmvc.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 请求处理器
 * @author lxf
 */
//@RequestMapping注解修饰类
@RequestMapping("/helloClass")
@Controller
public class HelloWorld {
    /**
     * １．使用@RequestMapping注解来映射请求的url，修饰方法
     * ２．返回值会通过视图解析器解析为实际的物理视图，对于InternalResourceViewResolver解析器，会做如下的解析：
     * 　　通过　prefix + returanval + 后缀，这样的方式得到实际的物理视图，然后做转发操作
     *         /WEB-INF/views/success.jsp
     * @return
     */
    @RequestMapping("/helloMethod")
    public String hello()
    {
        System.out.println("Hellow world!");
        return "success";
    }
}
