package com.lxf.timerintercepter;
/**
 * 做一个耗时的actioin,用拦截器统计该action执行的时间
 */

import com.opensymphony.xwork2.ActionSupport;

public class TimerAction extends ActionSupport{

    @Override
    public String execute() throws Exception {
            for(int i=0;i<10000;i++)
            {
                System.out.println("I LOVE IMOOC");
            }
            return SUCCESS;
    }
    
}
