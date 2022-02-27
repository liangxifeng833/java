package src.com.lxf.tenChapter;

/**
 * 接口内部类demo
 */
public interface ClassInInterface {
    void howdy();
    //可以在接口内部嵌套类中实现任何外部接口
    class Test implements ClassInInterface {
        public void howdy() {
            System.out.println("Howdy");
        }
        public static void main(String[] args) {
            new Test().howdy();
        }
    }
    class Test2 {}
}
