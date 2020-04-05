package com.atguigu.springmvc.tests;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.atguigu.springmvc.crud.dao.EmployeeDao;
import com.atguigu.springmvc.crud.entities.Employee;

@Controller
public class SpringMVCTestNew {
    @Autowired
    private EmployeeDao employDao ;
    @Autowired
    private ResourceBundleMessageSource messageSource;//用来获取国际化资源文件用的
    
    /**
     * 测试SimpleMappingExceptionResolver映射指定异常到对应页面
     * 如果页面传递的参数是20 ,超出数组边界, 回抛出数组越界异常, 
     * 在springmvc.xml文件中配置映射关系
     * @param i
     * @return
     */
    @RequestMapping("/testSimpleMappingExceptionResolver")
    public String testSimpleMappingExceptionResolver(@RequestParam("i") int i)
    {
        String[] arr = new String[10];
        String val = arr[i];
        System.out.println("testSimpleMappingExceptionResolver val = " + val);
        return "success";
    }
    /**
     * 测试异常处理DefaultHandlerExceptionResolver处理springMVC指定的异常返回对应Http状态
     * 该处理器必须是post方式请求,如果请求方式为get那么会通过DefaultHandlerExceptionResolver处理
     * @return
     */
    @RequestMapping(value="/testDefaultHandlerExceptionResolver", method=RequestMethod.POST)
    public String testDefaultHandlerExceptionResolver()
    {
        System.out.println("testDefaultHandlerExceptionResolver");
        return "success";
    }
    
    /**
     * 测试 @ResponseStatus 注解, 将http状态码返回客户端
     * @param i
     * @return
     */
    @ResponseStatus(value=HttpStatus.NOT_FOUND, reason="您访问的页面不存在!")
    @RequestMapping("/testResponseStatusExceptionResolver")
    public String testResponseStatusExceptionResolver(@RequestParam("i") int i)
    {
        if(i == 13)
        {
            throw new UserNameNotMatchPasswordException();
        }else
        {
            System.out.println("testResponseStatusExceptionResolver...");
        }
        return "success";
    }
    
//    @ExceptionHandler({RuntimeException.class})
//    public ModelAndView handlerArithmeticException2(Exception ex)
//    {
//        ModelAndView mv = new ModelAndView("error");
//        mv.addObject("exception", ex);
//        System.out.println("[出现了运行时异常] : " + ex);
//        return mv;
//    }
    /**
     * 1. 在 @ExceptionHandler 方法的入参中可以加入 Exception 类型的参数, 该参数即对应发生的异常对象
     * 2. @ExceptionHandler 方法的入参中不能传入 Map. 若希望把异常信息传导页面上, 需要使用 ModelAndView 作为返回值
     * 3. @ExceptionHandler 方法标记的异常有优先级的问题. (优先会找与出现异常最接近的异常来处理)
     * 4. @ControllerAdvice: 如果在当前 Handler 中找不到 @ExceptionHandler 方法来出来当前方法出现的异常, 
     * 则将去 @ControllerAdvice 标记的类中查找 @ExceptionHandler 标记的方法来处理异常. 
     */
    @ExceptionHandler({ArithmeticException.class})
    public ModelAndView handlerArithmeticException(Exception ex)
    {
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("exception", ex);
        System.out.println("[出现了算数异常] : " + ex);
        return mv;
    }
    
    /**
     * 测试springMVC对于异常的处理
     */
    @RequestMapping("/testExceptionHandlerReslover")
    public String testExceptionHandlerReslover(@RequestParam(value="i") Integer i)
    {
        System.out.println("param i value = " + (10/i));
        return "success";
    }
    
    /**
     * 测试文件上传
     * @param desc
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping("/testFileUpload")
    public String testFileUpload(@RequestParam("desc") String desc, 
            @RequestParam("file") MultipartFile file) throws IOException{
        System.out.println("desc: " + desc);
        System.out.println("上传文件的原文件名: " + file.getOriginalFilename());
        System.out.println("输入流信息: " + file.getInputStream());
        return "success";
    }
    
    /**
     * 目标方法获取国际化配置文件属性
     * @param locale
     * @return
     */
    @RequestMapping("/testI18n")
    public String testI18n(Locale locale){
        String val = messageSource.getMessage("i18n.user", null, locale);
        System.out.println(val); 
        return "i18n";
    }
    
    /**
     * 测试文件下载
     * @param session
     * @return
     * @throws IOException
     */
    @RequestMapping("/testResponseEntity")
    public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException{
        byte [] body = null;
        ServletContext servletContext = session.getServletContext();
        //注意:answers.txt必须存在项目的根目录下即webContent目录下
        InputStream in = servletContext.getResourceAsStream("/answers.txt");
        ResponseEntity<byte[]> response = null;
        if(in != null)
        {
            body = new byte[in.available()];
            in.read(body);          
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment;filename=answers.txt");         
            HttpStatus statusCode = HttpStatus.OK;        
            response = new ResponseEntity<byte[]>(body, headers, statusCode);               
        }else
        {
            System.out.println("file is null");
        }
        return response;
    }
    
    /**
     * 测试直接返回字符串
     * @param body 参数@RequestBody String body接收的是用户上传过来的文件
     * @return
     */
    @ResponseBody
    @RequestMapping("/testHttpMessageConverter")
    public String testHttpMessageConverter(@RequestBody String body){
        System.out.println(body);
        return "helloworld! " + new Date();
    }
    
    /**
     * 测试返回json数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/testJson")
    public Collection<Employee> testJson()
    {
        return employDao.getAll();
    }
    
    @RequestMapping("/testRest")
    public String testRest()
    {
        System.out.println("hellow testRest");
        return "redirect:/emps";
    }
    
    @RequestMapping(value="/testConversionServiceConverer")
    public String testConverter(@RequestParam(value="employee") Employee employee)
    {
        System.out.println("testConverter param employee String to Employee Obj ="  + employee);
        employDao.save(employee);
        return "redirect:/emps";
    }
}
