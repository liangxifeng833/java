package src.com.lxf;

/**
 * 静态初始化块练习
 * @author liangxifeng
 * @date 2020-11-23
 */
public class Cup {
    Cup(int marker) {
        System.out.println("Cup("+marker+")");
    }
    void f(int marker) {
        System.out.println("f("+marker+")");
    }
}

class Cups {
    static Cup cup1;
    static Cup cup2;
    static {
        cup1 = new Cup(1);
        cup2 = new Cup(2);
    }
    Cups() {
        System.out.println("Cups()");
    }
}

class staticStaticInit {
    public static void main(String[] args) {
        System.out.println("inside main()");
        //Cups.cup1.f(99);
    }
    static Cups cups2 = new Cups();
    static Cups cups1 = new Cups();
}
