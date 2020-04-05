package lxf.spring.aop.impl;
/**
 * 基于使用注解的方式配置AOP测试类
 */
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
            //从Spring中创建IOC容器
            ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
            //从IOC容器中获取Bean
            CalculatorInterface cal = (CalculatorInterface)ctx.getBean("calculatorImpl");
            System.out.println(cal.getClass().getName());//输出：com.sun.proxy.$Proxy8代理对象，而不是真正的CalculatorImpl
            int res = cal.add(4, 2);
            System.out.println(res);        
            int res2 = cal.div(4, 2);
    }
}
