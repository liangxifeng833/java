package src.com.lxf;

import java.util.Arrays;
import java.util.Random;

/**
 * 数组的初始化联系
 */
public class ArrayClassObj {
    public static void main(String[] args) {
        Random rand = new Random(47);
        System.out.println(rand.nextInt(10));
        System.out.println(rand.nextInt(10));
        System.out.println(rand.nextInt(10));
        Integer[] a = new Integer[3];
        System.out.println("length of a = "+a.length);
        for (int i = 0; i < a.length; i++)
        {
            a[i] = rand.nextInt(500);
        }
        System.out.println(Arrays.toString(a));

        //使用花括号方式初始化数组
        Integer[] a1 = {
                new Integer(1),
                new Integer(2),
                3 //自动装箱
        };

        Integer[] b1 = new Integer[]{
                new Integer(1),
                new Integer(2),
                3 //自动装箱
        };
        System.out.println("a1 = "+Arrays.toString(a1));
        System.out.println("b1 = "+Arrays.toString(b1));
    }
}
