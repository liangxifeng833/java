package com.example.demo.javaEight.stream;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * java8 streams 练习
 */
public class StreamsDemo {
    public static void main(String[] args) {
        //静态方法of()创建Stream，通过接收可变长数组的方式获取了一个T类型的Stream
        Stream<Integer> stream1 = Stream.of(1,2,3,4);
        //stream1.forEach(x -> System.out.println(x));
        stream1.forEach(System.out::println);

        //list 的 foreach
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        list.forEach(System.out::println);


        //map 的foreach
        Map<String,Object> map = new HashMap<>();
        map.put("a","12");
        map.put("b","34");
        //遍历map
        map.forEach((x,y)-> System.out.println("key=" + x + ", value=" + y));
        //map.values 的 foreach1
        map.values().forEach(System.out::println);

        System.out.println("++++++++++++++++++++distinct去重操作+++++++++++++++++++++");
        Stream<Integer> stream2 = Stream.of(1,2,3,4,1,2);
        stream2.distinct().forEach(i -> System.out.println(i));

        //filter 过滤器使用 先筛选出>2的数字，然后使用foreach输出
        list.stream().filter(integer -> integer>2).forEach(System.out::println);

        System.out.println("++++++++++++++++++++limit操作+++++++++++++++++++++");
        Stream<Integer> stream3 = Stream.of(11,22,33,44);
        //limit 3 截取前三条数据
        stream3.limit(3).forEach(i -> System.out.println(i));

        //map映射操作，可以借助map操作对元素进行增强运算、投影运算，甚至类型转换等操作
        System.out.println("++++++++++++++++++++map操作+++++++++++++++++++++");
        List<Integer> list1 = Arrays.asList(1,2,3);
        //对list中每个元素*2,最后输出
        list1.stream().map(i -> i*2).forEach(System.out::println);

        //skip丢弃前n个元素，skip操作与limit类似，但是其作用却是相反的
        System.out.println("++++++++++++++++++++skip操作+++++++++++++++++++++");
        list1.stream().skip(2).forEach(System.out::println);

        System.out.println("++++++++++++++++++++sorted操作+++++++++++++++++++++");
        Stream<Integer> stream5 = Stream.of(3,4,1,2,6,5);
        stream5.sorted().forEach(i -> System.out.println(i));

        System.out.println("++++++++++++++++++++match操作+++++++++++++++++++++");
        Stream<Integer> stream6 = Stream.of(3,4,1,2,6,5);
        System.out.println(stream6.allMatch(integer -> integer > 0)); //匹配所有元素都>0 输出：true

        System.out.println("++++++++++++++++++++foreach操作 可以对比在并发情况下，输出顺序是不一样的+++++++++++++++++++++");
        Stream<Integer> stream7 = Stream.of(1,2,3,4).parallel(); //并发无顺序
        stream7.forEach(i -> System.out.print(i + " "));
        Stream<Integer> stream8 = Stream.of(1,2,3,4).parallel(); //并发有顺序
        //stream8.forEachOrdered(System.out::print);
        Integer max = stream8.max(Comparator.comparingInt(o->o)).get();
        System.out.println(max);

    }
}
