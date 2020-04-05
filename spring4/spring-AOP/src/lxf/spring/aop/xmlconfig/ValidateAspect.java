package lxf.spring.aop.xmlconfig;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 验证切面
 * @author lxf
 */
//使用Order接口指定一个连接点处多个切面的优先级，值越小越优先执行
public class ValidateAspect {
    /*
     * 前置通知：在lxf.spring.aop.impl.CalculatorInterface接口中的每个方法开始执行之前执行一段代码
     */
    public void myBefore(JoinPoint joinPoint)
    {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("=====>Validate methodName = " + methodName + "，参数＝" + args);
    }
}
