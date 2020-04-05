package com.lxf.spring.bean;

import java.util.List;
import java.util.Map;

public class Person {
    //姓名
    private String name;
    //年龄
    private int age;
    //该人所拥有的汽车
    private Car car;
    //该人所拥有的汽车集合
    private List<Car> cars;
    //map类型的汽车
    private Map<String,Car> mapCars;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public Car getCar() {
        return car;
    }
    public void setCar(Car car) {
        this.car = car;
    }
    
    public List<Car> getCars() {
        return cars;
    }
    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
    
    public Map<String, Car> getMapCars() {
        return mapCars;
    }
    public void setMapCars(Map<String, Car> mapCars) {
        this.mapCars = mapCars;
    }
    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + ", car=" + car + ", cars=" + cars + "mapCars=" + mapCars +"]";
    }
    public Person(String name, int age, Car car) {
        super();
        this.name = name;
        this.age = age;
        this.car = car;
    }
    public Person() {
        super();
    }
    
}
