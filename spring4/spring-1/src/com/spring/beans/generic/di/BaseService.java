package com.spring.beans.generic.di;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Service父类，泛型为T类型 
 * @author lxf
 * @param <T>
 */
public class BaseService{
    @Autowired
    protected BaseRespository respository;
    public void add()
    {
        System.out.println("BaseService add...");
        System.out.println("BaseService.respository=" + respository);
    }
}
