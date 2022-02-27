package src.com.lxf.sevenChapter;

import com.sun.corba.se.impl.resolver.SplitLocalResolverImpl;

import java.util.Random;

class Value {
    int i; //默认包访问权限
    public Value(int i) {
        this.i = i;
    }

    @Override
    public String toString() {
        return "i="+i;
    }
}
public class FinalData {
    private static Random rand = new Random(47);
    private String id;
    public FinalData(String id){this.id = id;}

    // final基本类型 可以用作编译器常量
    private final int valueOne = 9;
    private final int VALUE_TWO = 99;
    /**
     * VALUE_THREE典型的对于常量的定义：
     * 1.定义为public可以被用于包外
     * 2.定义为static,强调只有一份
     * 3.定义为final,说明它是一个常量
     */

    public static final int VALUE_THREE = 39;

    //final引用类型不可以用作编译器常量
    private final int i4 = rand.nextInt(20);
    static final int INT_5 = rand.nextInt(20);
    private Value v1 = new Value(11);
    private final Value v2 = new Value(22);
    private static final Value VAL_3 = new Value(33);

    //Arrays
    private final int[] a = {1,2,3,4,5,6};
    public String toString() {
        return id + ": i4=" + i4 + ", INT_5 = "+ INT_5;
    }

    public static void main(String[] args) {
        FinalData fd1 = new FinalData("fd1");
        //基本类型的常量值不可被修改
        //fd1.valueOne++

        //常量引用不能重新赋值，但是引用的对象内容可以被修改
        //fd1.v2 = new Value(33);
        //以下是final类型引用对象值被修改了
        fd1.v2.i++; //v1.i=23

        //final类型的数组类型，数组值可以被修改，因为数组也是对象,
        for (int i = 0; i < fd1.a.length; i++) {
           fd1.a[i]++;
        }
        //但数组引用不能被重新赋值
        //fd1.a = new int[3];
        System.out.println(fd1);
        System.out.println("Creating new FinalData");
        FinalData fd2 = new FinalData("fd2");
        System.out.println(fd1);
        System.out.println(fd2);

        Random random1 = new Random(13);
        Random random2 = new Random(13);
        System.out.println("random1="+random1.nextInt(20));
        System.out.println("random1="+random1.nextInt(20));
        for (int i = 0; i < 10; i++) {
            System.out.print(random1.nextInt(10));
        }
        System.out.println("=========================");
        for (int i = 0; i < 10; i++) {
            System.out.print(random2.nextInt(10));
        }
    }

}
