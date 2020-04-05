package com.atguigu.springmvc.interceptors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/**    
 * springMVC下第一个拦截器学习   
 * @author lxf
 *
 */
public class FirstInterceptor implements HandlerInterceptor{

    /**
     * 在渲染视图之后被调用, 释放资源
     */
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        System.out.println("FirstInterceptor afterCompletion");  
    }
    /**
     * 调用目标方法之后但渲染视图之前被调用
     * 可以对请求域中的属性修改
     */
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
        System.out.println("FirstInterceptor postHandle");
    }
    /**
     * 在目标方法调用之前被调用
     * 若返回值为true, 则继续调用后续的拦截器和目标方法
     * 若返回值为false, 则不会调用后续的拦截器和目标方法
     */
    @Override
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
        System.out.println("FirstInterceptor preHandle");
        return true;
    }
}
