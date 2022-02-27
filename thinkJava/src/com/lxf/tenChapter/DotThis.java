package src.com.lxf.tenChapter;

import sun.font.CreatedFontTracker;

/**
 * 使用.this和.new
 */
public class DotThis {
    void f() {
        System.out.println("DotThis.f()");
    }
    public class Inner {
        public String a = "1";
        public DotThis outer() {
            return DotThis.this;
        }
    }
    public static class Inner2 {
        private static int a = 1;
        private int b = 2;
        public void testA(){}
        public String testB(){ return null;}
    }
    public Inner inner() {
        return new Inner();
    }

    public static void main(String[] args) {
        DotThis dt = new DotThis();
        DotThis.Inner dti = dt.inner();
        dti.outer().f();
    }
}
