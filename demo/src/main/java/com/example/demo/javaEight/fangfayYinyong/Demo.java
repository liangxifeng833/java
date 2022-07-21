package com.example.demo.javaEight.fangfayYinyong;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

/**
 * java8 方法引用
 * 三种使用情况
 *  1. 对象::实例方法名（非静态方法）
 *  2. 类::静态方法名
 *  3. 类::实例方法名
 */
public class Demo {
    public static void main(String[] args) {
        Consumer<String> con = x -> System.out.println(x);
        con.accept("hello,zhangsan");
        /**
         *  1. 对象::实例方法名（非静态方法）
         * Consumer: 方法 void accept(T t)
         * 方法体中用的方法：void println(String x)
         * 如果满足上面的要求：前面的函数式接口的参数和返回值 和 具体方式体实现中的方法的 参数 和返回值一致，那么可以使用方法引用
         */
        PrintStream ps = System.out;
        Consumer<String> con2 = ps::println;
        con2.accept("hello,zhangsan");

        /**
         *  2. 类::静态方法名
         *  int compare(T o1, T o2);
         *  int compare(int x, int y);
         */
        // 比较两个数字大小
        Comparator<Integer> com = (x,y) -> Integer.compare(x,y);
        System.out.println(com.compare(100,200)); //-1
        Comparator<Integer> co2 = Integer::compare;
        System.out.println(co2.compare(1,2)); // -1

        /**
         *  3. 类::实例方法名
         *  使用这个方法引用的前提是：x 作为方法的调用者，y作为方法的实际参数
         */
        //比较两个字符串是否相等
        BiPredicate<String,String> bp = (x,y) -> x.equals(y);
        System.out.println( bp.test("abc","abc") ); //true

        BiPredicate<String,String> bp2 = String::equals;
        System.out.println( bp2.test("abc","abc")); //true

    }
}
