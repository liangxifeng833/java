package proxy.statics;
/**
 * 使用继承的方式实现静态代理
 * @author lxf
 * @date 2017-07-06
 */
public class ProxyCar2 extends Car {

    @Override
    public void move() {
        //开始时间
       Long start = System.currentTimeMillis();
       System.out.println("汽车开始行驶...");
        super.move();
        //结束时间
        Long end = System.currentTimeMillis();
        System.out.println("汽车结束行驶..." + "行驶话费了：" + (end-start) + "ms");
    } 
}
