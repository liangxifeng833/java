package proxy.dynamic.cglib;

import java.beans.Encoder;
import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
/**
 * 用cglib方式实现动态代理（原理就是继承的方式）
 * @author lxf
 *
 */

public class CglibProxy implements MethodInterceptor {
    private Enhancer enhancer = new Enhancer();
    
    /**
     * 创建代理类
     * @param c
     * @return
     */
    public Object getProxy(Class c)
    {
        //设置创建子类的类
        enhancer.setSuperclass(c);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    /**
     * 拦截所有目标类方法的调用
     * 参数：
     * obj 目标类的实例
     * method 目标方法的反射对象
     * args 方法的参数列表
     * proxy 代理类的实例
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("火车运行开始...");
        //代理类调用父类的方法
        proxy.invokeSuper(obj, args);
        System.out.println("火车运行结束...");
        return null;
    }
    
}
