package com.lxf.spring.spel;
/**
 * 人实体类
 * @author lxf
 *
 */
public class Person {
    private String  name;
    private Car car;
    //所在城市
    private String city;
    //该人的工作，如果开30万以上的车就是金领，否则为白领；
    private String work;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Car getCar() {
        return car;
    }
    public void setCar(Car car) {
        this.car = car;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getWork() {
        return work;
    }
    public void setWork(String work) {
        this.work = work;
    }
    @Override
    public String toString() {
        return "Person [name=" + name + ", car=" + car + ", city=" + city + ", work=" + work + "]";
    }
    
    
}
