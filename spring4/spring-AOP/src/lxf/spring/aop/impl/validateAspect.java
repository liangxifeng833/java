package lxf.spring.aop.impl;

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
@Order(1)
@Aspect
@Component
public class validateAspect {
    /*
     * 前置通知：在lxf.spring.aop.impl.CalculatorInterface接口中的每个方法开始执行之前执行一段代码
     * 声明该方法是一个前置通知，在执行目标方法前执行, *代表匹配所有方法，..代表匹配任意参数
     */
    @Before("LogAspect.declareJointPointExpression()")
    public void myBefore(JoinPoint joinPoint)
    {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("=====>Validate methodName = " + methodName + "，参数＝" + args);
    }
}
