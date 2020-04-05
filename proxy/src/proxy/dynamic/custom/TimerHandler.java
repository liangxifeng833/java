package proxy.dynamic.custom;
import java.lang.reflect.Method;
/**
 * 实现自己定义的事务处理接口proxy.dynamic.custom.InvocationHandler
 * @author lxf
 *
 */
public class TimerHandler implements  InvocationHandler {
    //代理对象
    private Object target;
   
    public TimerHandler(Object target) {
        super();
        this.target = target;
    }

    /**
     * 参数：Object obj 属于代理对象
     * 参数：Method method 属于代理对象的方法
     */
    @Override
    public void myinvoke(Object obj, Method method) {
        // TODO Auto-generated method stub
        try {
            //开始时间 " + br + 
            Long start = System.currentTimeMillis(); 
            System.out.println("汽车开始行驶...");
            method.invoke(target);
            //结束时间 " + br + 
            Long end = System.currentTimeMillis(); 
            System.out.println("汽车结束行驶...行驶话费了：" + (end-start) + "ms"); 
            //视频听到3-3 3:42秒
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
    }
}
