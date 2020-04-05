package proxy.dynamic.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import proxy.statics.Car;
import proxy.statics.MoveAble;
/**
 * JDK动态代理测试类
 * @author lxf
 *
 */
public class TestMain {
    public static void main(String[] args)
    {
        Car  car = new Car();
        InvocationHandler h  = new TimerHandler(car);
        Class<?> cls = car.getClass();    
        /**
         * 参数：
         * loader 被代理类的类加载器
         * interfaces 被代理类实现的接口
         * invocationHandle 事务处理器（真正的代理业务罗辑）
         */
        //Proxy.newProxyInstance(loader, interfaces, arg2);
        //动态实例话代理对象
        MoveAble m = (MoveAble)Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), h);
        m.move();
    }
}
