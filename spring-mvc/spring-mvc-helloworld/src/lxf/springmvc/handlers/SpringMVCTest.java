package lxf.springmvc.handlers;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import lxf.springmvc.entity.User;
/**
 * 测试spring-mvc
 * @author lxf
 *
 */
/*
 * 使用@SessionAttributes注解将模型中的数据放在session作用域中
 * 可以存指定字符串，也可以存指定类型
 */
@SessionAttributes(value={"user"},types={String.class})
@RequestMapping("springmvc")
@Controller
public class SpringMVCTest {
    public static final String SUCCESS = "success";
    /**
     *  测试重定向
     */
    @RequestMapping(value="testRedirect")
    public String testRedirect()
    {
        System.out.println("testRedirect");
        //重定向
        return "redirect:/index.jsp";
        //内部转发
        //return "forward:/index.jsp";
    }
    /**
     * 测试自定义视图
     */
    @RequestMapping("/testView")
    public String testView(){
        return "helloView";
    }
    /**
     * 视图解析测试
     * @return
     */
    @RequestMapping(value="testViewAndViewResolver")
    public String testViewAndViewResolver()
    {
        System.out.println("testViewAndViewResolver");
        return SUCCESS;
    }
    /**
     * １．用@ModelAttribute注解标记的方法，会在每个目标方法执行执之前被SpringMVC调用
     * ２．@ModelAttribute注解也可以修饰目标方法POJO类型的入参，起value属性有如下作用：
     * 　　１）．SpringMvc会使用value属性值在implicitModel中查找对应的对象，若存在则会直接传入到目标方法的入参中．
     * 　　２）．SpringMvc会以value为key, POJO类型对象为value，存入到request请求域中
     * @param id
     * @param map
     */
    @ModelAttribute
    public void getUser(@RequestParam(value="id",required=false) Integer id,
            Map<String,Object>map){
        if(id!=null){
            //模拟从数据库中获取对象
            User user = new User(1, "Tom", "123456", null);
            System.out.println("从数据中获取的对象是User = " + user);
            map.put("user", user);
        }
    }
    /**
     * 测试ModelAttribute注解，可以在表单提交之前，对对应的实体属性先赋值
     *  运行流程：
     *  １．执行@ModelAttribute注解修饰的方法：从数据库中取出对象，把对象放入Map中，键为：user
     *  ２．SpringMVC从Map中去取出User对象，并把表单的请求参数赋值给该User对象的对应属性
     *  ３．SpringMVC把上述传入目标方法的参数；
     *  注意：在@ModelAttribute修饰方法中，放入到Map时的键需要和目标方法入参类型的第一个字母小写的字符串一致；

     * @param user
     * @return
     */
    @RequestMapping("/testModelAttribute")
    //该方法入参@ModelAttribute(value="user")如果不写是默认的，如果写则必须和getUser方法中的map.put("user", user)的key对应
    public String testModelAttribute(@ModelAttribute(value="user") User user)
    {
        System.out.println("修改：" + user);
        return SUCCESS;
    }
    /**
     *  使用@SessionAttributes注解将模型中的数据放在session作用域中
     * @param map
     * @return
     */
    @RequestMapping("/testSessionAttributes")
    public String testSessionAttributes(Map<String,Object>map)
    {
        User user = new User("Liangxifeng","20");
        //将user对象放到request请求域和Session域中，要保证@SessionAttributes(value={"user"},中value="user"
        map.put("user", user);
        //想request域和session域中存放key＝"lang"的字符串
        map.put("lang", "java");
        return SUCCESS;
    }
    /**
     * 
     * 目标方法可以添加Map类型（实际上也可以是Model类型或ModelMap类型）的参数
     * ＠param map
     */
    @RequestMapping("/testMap")
    public String testMap(Map<String,Object>map)
    {
        System.out.println(map.getClass().getName());
        map.put("names", Arrays.asList("Tom","Jerry","php"));
        System.out.println("testMap");
        return SUCCESS;
    }
    /**
     * 目标方法返回值是ModelAndView类型
     * 其中包含视图和模型信息
     * SpringMVC会把ModelAndView的model中的数据放到request域对象中
     */
    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView()
    {
        String viewName = SUCCESS;
        ModelAndView modelAndView = new ModelAndView(viewName);
        //添加模型数据到ModelAndView中
        modelAndView.addObject("time", new Date());
        return modelAndView;
    }
    /**
     * 使用原生Servlet API作为目标方法的参数　具体支持以下类型
     * HttpServletRequest
     * HttpServletResponse
     * HttpSession
     * java.security.Principal
     *  Local InputStream
     *  OutputStream
     *  Reader
     *  Writer
     */
    @RequestMapping("/testServletAPI")
    public void testServletAPI(HttpServletRequest request, HttpServletResponse response, Writer out)
    {
        System.out.println("testServletAPI + request: " + request + ", response : " + response);
        try {
            //向浏览器输出
            out.write("Hellow springMvc!");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * 测试使用Pojo对象绑定前台页面提交的表单数值
     * @param user
     * @return
     */
    @RequestMapping(value="testPojo")
    public String testPojo(User user)
    {
        System.out.println("测试使用Pojo对象绑定前台页面提交的表单数值,User对象= "+user);
        return SUCCESS;
    }
    /**
     * 使用@CookieValue注解，映射cookie值到对应方法的入参
     * @param cookie
     * @return
     */
    @RequestMapping(value="testRequestCookieValue")
    public String testRequestCookieValue(@CookieValue(value="JSESSIONID") String cookie)
    {
        System.out.println("testRequestCookieValue测试映射请求cookie值到对应方法的入参，使用@CookieValue注解: sessionId =  " + cookie);
        return SUCCESS;
    }
    /**
     * @RequestHeader注解，映射请求头信息到对应方法的入参
     * 用法同@RequestParam
     */
    @RequestMapping(value="testRequestHeader")
    public String testRequestHeader(@RequestHeader(value="Accept-Language") String lang)
    {
        System.out.println("testRequestHeader测试映射请求头信息到对应方法的入参，使用@RequestHeader注解: " + lang);
        return SUCCESS;
    }
    /**
     * @RequestParam 来映射请求参数
     * value 请求参数的参数名
     * required 该参数是否必填　默认true
     * defaultValue 请求参数默认值
     */
    @RequestMapping(value="testRequestPram", method=RequestMethod.GET)
    public String testRequestPram(@RequestParam(value="username") String uname,
                                                            @RequestParam(value="age",required=false,defaultValue="0" ) int age )
    {
        System.out.println("testRequestPram 测试请求参数映射到方法的入参,参数为：username=" + uname + "，　age = " + age);
        return SUCCESS;
    }
    /**
     * Rest风格的URL.
     * 以CURD为例：
     * 新增：/order POST 
     * 修改：/order/1 PUT   =======> update?id=1
     * 获取：/order/1 GET   =======> get?id=1
     * 删除：/order/1 DELETE======  delete?id=1
     * 
     * 如何发送PUT请求和DELETE请求呢？
     * １．需要配置HiddenHttpMethodFilter
     * ２．需要发送POST请求
     * ３．需要在发送POST请求时携带一个name="_method"的隐藏域，值为DELETE或PUT
     */
    /**
     * 比较常用：使用method属性来指定请求方式（post方式）
     * @return
     */
    @RequestMapping(value="testMethod", method=RequestMethod.POST)
    public String testMethod()
    {
        System.out.println("testMethod");
        return SUCCESS;    
    }   
    /**
     * Rest 测试PUT请求
     */
    @RequestMapping(value="/testRest/{id}", method=RequestMethod.PUT )
    public String testRestPut(@PathVariable(value="id") Integer newId)
    {
        System.out.println("testRestPut request id = " + newId);
        return SUCCESS;
    }
    /**
     * Rest 测试delete请求
     */
    @RequestMapping(value="/testRest/{id}", method=RequestMethod.DELETE)
    public String testRestDelete(@PathVariable(value="id") Integer newId)
    {
        System.out.println("testRestDelete request id = " + newId);
        return SUCCESS;  
    }

    
    /**
     * Rest测试post请求
     * @return
     */
    @RequestMapping(value="/testRest", method=RequestMethod.POST)
    public String testRestPost()
    {
        System.out.println("testRestPost request !");
        return SUCCESS;
    }

    
    /**
     * Rest测试get请求
     * @return
     */
    @RequestMapping(value="/testRest/{id}", method=RequestMethod.GET)
    public String testRestGet(@PathVariable(value="id") Integer newId)
    {
        System.out.println("testRestGet request id = " + newId);
        return SUCCESS;
    }
    /**
     * @PathVariable 注解可以用来映射URL中的占位符到目标方法的参数中
     * 访问：http://localhost:8081/spring-mvc-helloworld/springmvc/testPathVariable/2
     * 输出：testPathVariable 2
     * @return
     */
    @RequestMapping("/testPathVariable/{id}")
    public String testPathVariable(@PathVariable(value="id") Integer newId)
    {
        System.out.println("testPathVariable " + newId);
        return SUCCESS;
    }
    
    /**
     * 使用url地址通配符测试*代表任意字符
     * @return
     */
    @RequestMapping("/testAntPath/*/abc")
    public String testAntPath(){
            System.out.println("testAntPath");
            return SUCCESS;
    }
    /**
     * 测试使用@RequestMapping注解指定请求参数和请求头
     * 
     * 了解：使用params和headers属性规定请求参数信息和请求头信息
     * 以上规定请求参数列表必须包含useranme和age两个参数，
     * 请求头Accept-Language必须是zh-CN,zh;q=0.8,en;q=0.6，
     * 否则会出现404
     * 
     * 请求地址：http://localhost:8081/spring-mvc-helloworld/springmvc/testParamsAndHeaders?username=123&age=11
     */
    @RequestMapping(value="/testParamsAndHeaders", 
                                       params={"username","age!=10"},
                                       headers={"Accept-Language=zh-CN,zh;q=0.8,en;q=0.6"})
    public String testParamsAndHeaders()
    {
        System.out.println("testParamsAndHeaders");
        return SUCCESS;   
    }


}
