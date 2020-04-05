package com.lxf.spring.cycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {

    /**
     *  bean的init-method方法之后调用
     *  参数：
     *  bean： IOC传递进来的bean实例
     *  beanName：bean的id
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // TODO Auto-generated method stub
        if("car".equals(beanName))
        {
            System.out.println("bean的init-method方法之后调用："+bean);
        }      
        return bean;
    }
    /*
     * bean的init-method方法之前调用
     *  参数：
     *  bean： IOC传递进来的bean实例
     *  beanName：bean的id
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // TODO Auto-generated method stub
        if("car".equals(beanName))
        {
            System.out.println("bean的init-method方法之前调用："+bean);
            Car car = new Car();
            car.setBrand("大众");
            //对bean重新赋值
            bean = car;
        }       
        return bean;
    }
}
