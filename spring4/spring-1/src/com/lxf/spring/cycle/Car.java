package com.lxf.spring.cycle;
/**
 * 汽车实体类
 * @author lxf
 */
public class Car {
    private String brand;
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
    
    /**
     * Bean初始化方法,在IOC容器启动的时候调用
     */
    public void myInit()
    {
        System.out.println("创建了IOC容器：调用Bean的初始化方法");
    }
    /**
     * Bean销毁方法，在IOC容器关闭的时候调用
     */
    public void myDestroy()
    {
        System.out.println("调用Bean的销毁方法，IOC容器关闭了");
    }
}
