package src.com.lxf;

/**
 * 在方法中返回this关键字的使用实例
 * this关键字标示的是：　调用方法的那个对象的引用
 * @author liangxifeng
 * @date 2020-11-23
 */
public class Leaf {
    int i = 0;
    Leaf increment(){
        i++;
        return this;
    }
    void print(){
        System.out.println("i = "+i);
    }
    public static void main(String[] args) {
        Leaf x = new Leaf();
        //输出结果: i = 3
        x.increment().increment().increment().print();
    }
}
