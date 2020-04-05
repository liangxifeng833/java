package proxy.statics;
/**
 * 聚集的方式实现静态代理（日志代理类）
 * @author lxf
 * @date 2017-07-06
 */
public class ProxyLogCar  implements MoveAble{
    //被代理Car对象
    private MoveAble m;
    public ProxyLogCar(MoveAble m)
    {
        this.m = m;
    }
    @Override
    public void move()  {
       System.out.println("记录日志开始..");
        //执行被代理对象的方法
        m.move();
        System.out.println("记录日志结束...");
    }
}
