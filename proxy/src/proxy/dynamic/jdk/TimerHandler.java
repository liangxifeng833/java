package proxy.dynamic.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TimerHandler implements InvocationHandler {
    //被代理对象
    private Object  target;
    public TimerHandler(Object target) {
        super();
        this.target = target;
    }
    
     /**
     * 参数：
     * ＠param: proxy 代理对象
     * @method ：代理对象的方法
     * ＠args: 代理对象的方法的参数
     * 
     * 返回值：
     * 被代理的对象方法的返回值
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //开始时间
       Long start = System.currentTimeMillis();
       System.out.println("汽车开始行驶...");
       //使用反射调用被代理对象的方法
        method.invoke(target);
        //结束时间
        Long end = System.currentTimeMillis();
        System.out.println("汽车结束行驶..." + "行驶话费了：" + (end-start) + "ms");
        return null;
    }

}
