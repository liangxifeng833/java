package proxy.statics;
import java.util.Random;
/**
 * 被代理类，汽车
 * @author lxf
 * @2017-07-06
 */
public class Car implements MoveAble {

    @Override
    public void move() {
        //System.out.println("被代理对象---我是汽车对象本身："+this.getClass().getName());
       //实现开车
       try {
            Thread.sleep(new Random().nextInt(1000));
            System.out.println("汽车行驶中...");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
