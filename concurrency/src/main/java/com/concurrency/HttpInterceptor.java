package com.concurrency;

import com.concurrency.example.threadLocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description: 自定义拦截器
 * Create by liangxifeng on 19-7-25
 */
@Slf4j
public class HttpInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle+++++++++");
        //return super.preHandle(request, response, handler);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //super.afterCompletion(request, response, handler, ex);
        //拦截器，当视图渲染完成后执行afterCompletion，移除ThreadLocal中的线程id
        RequestHolder.remove();
        log.info("afterCompletion+++++++++");
        return;
    }
}
