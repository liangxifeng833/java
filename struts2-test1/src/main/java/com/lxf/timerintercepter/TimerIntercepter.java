package com.lxf.timerintercepter;
/**
 * 拦截器，用来计算timerAction的执行时间
 */

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class TimerIntercepter extends AbstractInterceptor {

    /**
     * 自动调用此方法，进行拦截操作
     */
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
           //１．执行Action之前
           long start = System.currentTimeMillis(); //获得当前的毫秒值
           //２．执行下一个拦截器，如果已经是最后一个拦截器，则执行目标action
           String res = invocation.invoke();
           //３．执行Action之后
           long end = System.currentTimeMillis(); 
           System.out.println("执行Action话费的时间：" + (end - start) + "ms");
           //将最终调用Action之后的结果视图返回   
           return res;
    }

}
