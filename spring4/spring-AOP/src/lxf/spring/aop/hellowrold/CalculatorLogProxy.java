package lxf.spring.aop.hellowrold;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * 计算器日志代理类
 * @author lxf
 */
public class CalculatorLogProxy {
    //被代理的对象
    private CalculatorImpl target;    
    public CalculatorLogProxy(CalculatorImpl target) {
        super();
        this.target = target;
    }

    //获得动态代理对象
    public CalculatorInterface getLogProxy()
    {
        //代理对象由哪一个类加载器负责加载
        ClassLoader loader = target.getClass().getClassLoader();
        //代理对象的类型，以获得其中有哪些方法
        Class[] interfaces = new Class[]{CalculatorInterface.class};
        //当调用代理对象其中的方法时，该执行的代码
        InvocationHandler h = new InvocationHandler() {      
            /**
             * proxy：正在返回的那个代理对象，一般情况下，在invoke方法中不用该对象
             * method：正在被调用的方法
             * args：调用方法时传入的参数
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //记录日志
                System.out.println("日志－方法执行开始：调用方法名 " + method.getName() + "，参数是：" + Arrays.asList(args) );
                //执行方法
                
                Object res = null;
                try {
                    //前置通知的地方
                    res = method.invoke(target, args);
                    //返回通知的地方，可以访问到方法的返回值
                    
                } catch (Exception e) {
                    e.printStackTrace();
                    //异常通知的地方，可以访问到方法出现的异常
                }         
                //后置通知的地方，因为方法可能会出现异常，所以访问不到方法的返回值
                //记录日志   
                System.out.println("日志－方法执行结束：方法执行结束后返回值为：" +  res );          
                return res;
            }
        };
        //创建动态代理类
        CalculatorInterface calProxy = (CalculatorInterface)Proxy.newProxyInstance(loader, interfaces, h);
        return calProxy;
    }
}
