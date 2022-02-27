package src.com.lxf.tenChapter;

/**
 * 使用非静态内部类实现闭包
 */
//老师接口，work方法代表讲课功能
interface Teachable {
    public void work();
}
//程序员基类，work方法代表写程序功能
class Programer {
    protected String name;
    public Programer(String name) {
        super();
        this.name = name;
    }
    public void work(){
        System.out.println(name+"正在编程!");
    }
}
/**
 * TeachableProgrammer即是老师又是程序员
 * 该类继承Programer类，自动就有了编程功能，此时就没有办法实现Teachable接口了
 * 因为work方法名重复，所以只能使用内部类实现Teachable接口（该内部类就是闭包）
 * 然后在内部类中实现接口的work方法，在该方法中调用TeachableProgrammer中私有方法teach()实现教学任务；
 * 以上闭包（内部类）内调用外部类私有方法的功能又叫做回调。
 */
public class TeachableProgrammer extends Programer{
    public TeachableProgrammer(String name) {
        super(name);
    }
    //私有方法，教学工作任然由TeachableProgrammer定义
    private void teach() {
        System.out.println(name+"正在讲课");
    }
    //内部类（闭包）
    private class Closore implements Teachable {
        @Override
        public void work() {
            // 非静态内部类实现Teachable的work方法，作用仅仅是向客户类提供一个回调外部类的途径
            TeachableProgrammer.this.teach();
        }
        // 返回一个非静态内部类的引用,允许外部类通过该引用来回调外部类的方法
    }
    public Teachable getCallbackReference() {
        return new Closore();
    }
    public static void main(String[] args) {
        TeachableProgrammer tp = new TeachableProgrammer("张三");
        // 直接调用TeachableProgrammer从Programmer类继承下来的work方法,编程功能
        tp.work();
        // 表面上看是调用的Closure的work方法，实际上是通过通过work方法回调TeachableProgrammer的teach方法
        tp.getCallbackReference().work();
    }
}
