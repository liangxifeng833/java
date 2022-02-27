package src.com.lxf.tenChapter;

/**
 * 局部内部类demo
 */
interface Counter {
    int next();
}
public class LocalInnerClass {
    private int count = 0;
    Counter getCounter(String name) {
        //在方法中定义局部内部类
        class LoadCounter implements Counter {
            //局部内部类有构造器
            public LoadCounter() {
                System.out.println("LoadCounter");
            }
            public int next() {
                System.out.print(name);//可以访问方法作用域内部的局部变量
                return count++;
            }
        }
        return new LoadCounter();
    }
    Counter getCounter2(String name) {
        //使用匿名内部类实现相同的功能
        return new Counter() {
            {
                System.out.println("Counter");
            }
            @Override
            public int next() {
                System.out.print(name);//可以访问方法作用域内部的局部变量
                return count++;
            }
        };
    }

    public static void main(String[] args) {
        LocalInnerClass lic = new LocalInnerClass();
        Counter
            c1 = lic.getCounter("Local inner"),
            c2 = lic.getCounter2("Anonymous inner");
        for (int i = 0; i < 5; i++) {
            System.out.println(c1.next());
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(c2.next());
        }
    }
}
