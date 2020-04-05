package com.lxf.spring.bean;

public class HelloWorld {
    private String name;
    public void setName(String name)
    {
        System.out.println("setName:"+name);
        this.name = name;
    }
    public void hello()
    {
        System.out.println("hello: "+this.name);
    }
    public  HelloWorld()
    {
        System.out.println("init HelloWorld...");
    }
}
