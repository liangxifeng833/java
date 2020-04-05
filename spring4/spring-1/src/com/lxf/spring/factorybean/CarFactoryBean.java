package com.lxf.spring.factorybean;
import org.springframework.beans.factory.FactoryBean;
/**
 * 通过FactoryBean的方式配置Bean，需要实现FactoryBean接口
 * @author lxf
 * @param <T>
 */
public class CarFactoryBean<T> implements FactoryBean<T> {
    private String brand;
    public void setBrand(String brand) {
        this.brand = brand;
    }
    @Override
    /**
     * 返回bean对象
     */
    public T getObject() throws Exception {
        // TODO Auto-generated method stub
        return (T) new Car(brand,5000000);
    }
    @Override
    /**
     * 返回Bean类型
     */
    public Class<?> getObjectType() {
        // TODO Auto-generated method stub
        return Car.class;
    }
    @Override
    /**
     * 是否是单例
     */
    public boolean isSingleton() {
        // TODO Auto-generated method stub
        return true;
    }
}
