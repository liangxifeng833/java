package proxy.dynamic.cglib;

public class testMain {
    public static void main(String[] args)
    {
        //实例化cglibProxy代理类
        CglibProxy proxy = new CglibProxy();
        //实例火车类
        Train train = new Train();
        //获得代理类对象
        Train t = (Train)proxy.getProxy(train.getClass());
        //通过代理对象执行火车的move方法
        t.move();
    }
}
