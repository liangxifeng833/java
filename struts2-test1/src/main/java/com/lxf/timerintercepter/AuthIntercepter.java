package com.lxf.timerintercepter;
/**
 * 拦截器，用来验证用户登录
 */

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthIntercepter extends AbstractInterceptor {

    /**
     * 自动调用此方法，进行拦截操作
     */
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
           ActionContext context = ActionContext.getContext();
           Map<String,Object> session = context.getSession();
           //用户已登录
           if(session.get("loginInfo") !=null)
           {
               String result = invocation.invoke();
               return result;
           }else
            //用户未登录
           {
               return "login";
           }
    }

}
