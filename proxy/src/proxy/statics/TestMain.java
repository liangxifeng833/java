package proxy.statics;

public class TestMain {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
//        Car car = new Car();
//        car.move();
        
           //使用继承方式实现代理
//        MoveAble m = new ProxyCar2();
//        car2.move();
        
        //使用聚集的方式实现代理
        Car car = new Car();
        //先记录日志
        ProxyTimerCar tm = new ProxyTimerCar(car);
        //在记录时间
        ProxyLogCar lm = new ProxyLogCar(tm);
        lm.move();
    }

}
