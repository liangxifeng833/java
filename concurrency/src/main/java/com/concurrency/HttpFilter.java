package com.concurrency;


import com.concurrency.example.threadLocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Description: myFirst
 * Create by liangxifeng on 19-7-25
 */
@Slf4j
public class HttpFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request1 =  (HttpServletRequest) request;
        log.info("do filter ,{},{}",Thread.currentThread().getId(),((HttpServletRequest) request).getServletPath());
        RequestHolder.add(Thread.currentThread().getId());
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
