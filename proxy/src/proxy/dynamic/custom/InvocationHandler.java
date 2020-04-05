package proxy.dynamic.custom;

import java.lang.reflect.Method;

/**
 * 事务处理接口
 * @author lxf
 *
 */
public interface InvocationHandler {
    /**
     * 参数：Object obj 属于代理对象
     * 参数：Method method 属于代理对象的方法
     */
    public void myinvoke(Object obj, Method method);
}
