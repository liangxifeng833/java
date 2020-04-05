package lxf.spring.aop.xmlconfig;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
/**
 * 切面类
 * @author lxf
 */
public class LogAspect {   
    /*
     * 前置通知：在lxf.spring.aop.impl.CalculatorInterface接口中的每个方法开始执行之前执行一段代码
     */
    public void myBefore(JoinPoint joinPoint)
    {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("The method begins methodName = " + methodName + "，参数＝" + args);
    }
    /**
     * 后置通知：在lxf.spring.aop.impl.CalculatorInterface接口中的每个方法开始执行之后执行一段代码
     * 在目标方法执行之后（无论是否发生异常），执行的通知
     * 在后置通知中不能访问目标方法执行的结果
     */
    public void myAfter(JoinPoint joinPoint)
    {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method end methodName = " + methodName);      
    }
    /**
     * 返回通知：在方法正常结束处执行的代码
     * 是可以访问到方法的返回值的
     */
    public void myReturn(JoinPoint joinPoint,Object result)
    {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method return methodName = " + methodName + "返回值＝" + result);      
    }
    /**
     * 异常通知：在目标方法出现异常时会执行的代码
     * 可以访问到异常对象，并且可以指定在出现特定异常时在执行通知的代码
     * @param joinPoint
     * @param ex
     */
    public void myThrow(JoinPoint joinPoint, Exception ex)
    {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method  methodName = " + methodName + "出现了异常=" + ex);    
    }
    /**
     * 环绕通知需要携带ProceedingJoinPoint类型的参数
     * 环绕通知类似于动态代理的全过程，ProceedingJoinPoint类型的参数可以决定是否执行目标方法
     * @param pjd
     
    @Around("execution(* lxf.spring.aop.impl.CalculatorInterface.*(..))")
    public Object myArround(ProceedingJoinPoint pjd)
    {
        Object result = null;
        String methodName = pjd.getSignature().getName();
        List<Object> args = Arrays.asList(pjd.getArgs());
            try {
                //前置通知：
                System.out.println("The method begins methodName = " + methodName + "，参数＝" + args);
                //执行目标方法
                result = pjd.proceed(); //执行目标方法,相当于动态代理的method.invoke(target, args);
                //返回通知
                System.out.println("The method return methodName = " + methodName + "返回值＝" + result);      
            } catch (Throwable e) {
                //异常通知
                System.out.println("The method  methodName = " + methodName + "出现了异常=" + e);    
                //将异常继续抛出给运行时异常
                throw new RuntimeException(e);
                //e.printStackTrace();
            }
            //后置通知
            System.out.println("The method end methodName = " + methodName);      
            return result;
    }  */
}
