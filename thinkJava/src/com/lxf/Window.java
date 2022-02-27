package src.com.lxf;

/**
 * 练习类成员变量的初始化顺序
 * @author liangxifeng
 * @date 2020-11-23
 */
public class Window {
    Window(int marker) {
        System.out.println("Window("+ marker +")");
    }
}

class House {
    Window w1 = new Window(1);
    House(){
        System.out.println("House()");
        Window w3 = new Window(33);
    }
    Window w2 = new Window(2);
    void f() {
        System.out.println("f()");
    }
    Window w3 = new Window(3);
}

class OrderOfInit{
    public static void main(String[] args) {
        House h = new House();
        h.f();
    }
}
