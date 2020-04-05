package proxy.dynamic.custom;
import java.io.IOException;
import proxy.statics.Car;
import proxy.statics.MoveAble;
/**
 * 测试自己实现JDK动态代理实现思路
 * @author lxf
 * @date 2017-07-07
 */
public class testMain {
    public static void main(String[] args) throws IOException
    {
        //实例化被代理类
        Car car = new Car();
        //实例化事务处理器
        InvocationHandler h = new TimerHandler(car);
        //生成代理对象
        MoveAble m = (MoveAble)Proxy.newProxyInstance(MoveAble.class,h);
        //调用代理对象的方法
        m.move();
    }    
}
