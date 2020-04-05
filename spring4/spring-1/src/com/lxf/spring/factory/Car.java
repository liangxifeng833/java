package com.lxf.spring.factory;
/**
 * 汽车实体类
 * @author lxf
 */
public class Car {
    private String brand;
    public Car(String brand) {
        super();
        this.brand = brand;
    }
    private double price;
    //轮胎周长
    private double tyrePerimeter;
    /**
     * 无参数构造器
     */
    public Car()
    {
        System.out.println("Car is Constract...");
    }
    
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        System.out.println("setBrand为品牌属性赋值");
        this.brand = brand;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getTyrePerimeter() {
        return tyrePerimeter;
    }
    public void setTyrePerimeter(double tyrePerimeter) {
        this.tyrePerimeter = tyrePerimeter;
    }
    @Override
    public String toString() {
        return "Car [brand=" + brand + ", price=" + price + ", tyrePerimeter=" + tyrePerimeter + "]";
    }
}
