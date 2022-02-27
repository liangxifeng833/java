package src.com.lxf.tenChapter;
interface Api{
    int value();
}

/**
 * 嵌套（静态）内部类demo
 */
public class StaticInnerClass {
    private static class InnerClass implements Api {
        static int x = 10;
        @Override
        public int value() { return x; }
        public static void f() {
            System.out.println("我是静态内部类中的静态方法f()");
        }
    }
    public static Api api() {
        return new InnerClass();
    }
    public static void main(String[] args) {
        Api api = StaticInnerClass.api();
        api.value();
    }
}
