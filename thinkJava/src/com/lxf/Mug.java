package src.com.lxf;

import java.util.Arrays;
import java.util.Random;

/**
 * 非静态初始化块练习
 * @author liangxifeng
 * @date 2020-11-23
 */
public class Mug {
    Mug(int marker) {
        System.out.println("Mug("+marker+")");
    }
    void f(int marker){
        System.out.println("f("+marker+")");
    }
}
class Mugs{
    Mug mug1;
    Mug mug2;
    {
        mug1 = new Mug(1);
        mug2 = new Mug(2);
        System.out.println("mug1 & mug2 inited");
    }
    Mugs(){
        System.out.println("Mugs()");
    }
    Mugs(int i) {
        System.out.println("Mugs(int)");
    }

    public static void main(String[] args) {
        System.out.println("Inside main()");
        new Mugs();
        System.out.println("new Mugs() completed");
        new Mugs(1);
        System.out.println("new Mugs(1) completed");
        Random random = new Random(47);
        int i = random.nextInt(10);
        System.out.println(i);
        int[] a= new int[3];
        System.out.println(Arrays.toString(a));
    }
}
