package com.lxf.spring.bean;
public class Car {
    private String cName;
    private double price;
    private int speed;  
    private int testA;
    /**
     * 构造器，为汽车名字和汽车价格属性初始化值
     * @param cName
     * @param price
     */
    public Car(String cName, double price) {
        super();
        this.cName = cName;
        this.price = price;
    }

    /**
     * 重载构造器，为汽车名字和汽车速度初始化值
     * @param cName
     * @param speed
     */
    public Car(String cName, int speed) {
        super();
        this.cName = cName;
        this.speed = speed;
    }
    @Override
    public String toString() {
        return "Car [cName=" + cName + ", price=" + price + ", speed=" + speed + ", testA=" + testA + "]";
    }

    public void setcName(String cName) {
        this.cName = cName;
    }


    public void setPrice(double price) {
        this.price = price;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
}
