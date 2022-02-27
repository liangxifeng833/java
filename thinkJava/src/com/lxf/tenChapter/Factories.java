package src.com.lxf.tenChapter;

/**
 * 工厂方法与匿名内部类
 */
interface Service {
    void method1();
    void method2();
}
interface ServiceFactory {
    Service getService();
}
class Implementation1 implements Service {
    //私有构造器,该类不可被实例
    private Implementation1(){}
    //使用匿名内部类的方式定义工厂属
    public static ServiceFactory factory = new ServiceFactory() {
        @Override
        public Service getService() {
            return new Implementation1();
        }
    };
    @Override
    public void method1() {
        System.out.println("Implemention1 method1...");
    }
    @Override
    public void method2() {
        System.out.println("Implemention1 method2...");
    }
}
class Implementation2 implements Service {
    private Implementation2(){}
    public static ServiceFactory factory = new ServiceFactory() {
        @Override
        public Service getService() {
            return new Implementation2();
        }
    };
    @Override
    public void method1() {
        System.out.println("Implemention2 method1...");
    }
    @Override
    public void method2() {
        System.out.println("Implemention2 method2...");
    }
}

public class Factories {
    public static void serviceConsumer(ServiceFactory fact) {
        Service s = fact.getService();
        s.method1();
        s.method2();
    }

    public static void main(String[] args) {
        serviceConsumer(Implementation1.factory);
        serviceConsumer(Implementation2.factory);
    }
}
