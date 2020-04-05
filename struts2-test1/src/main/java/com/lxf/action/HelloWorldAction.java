package com.lxf.action;

import com.opensymphony.xwork2.ActionSupport;

public class HelloWorldAction  extends ActionSupport{

    @Override
    public String execute() throws Exception {
        // TODO Auto-generated method stub
        System.out.println("执行HelloWorld　action!");
        return SUCCESS;
    }
    /**
     * 新增方法
     */
    public void add()
    {
        System.out.println("我是新增方法");
    }
    /**
     *  我是修改方法
     */
    public String update()
    {
        System.out.println("我是修改方法");
        return "update";
    }
    
}
