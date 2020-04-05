package com.concurrency.example.immutable;

import com.concurrency.annoations.NotThreadSafe;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * final关键字对于普通类型变量不可修改,
 * 修饰引用类型变量, 可以修改变量对应对象的值但不可重新指向其他对象
 * Create by liangxifeng on 19-7-18
 */
@Slf4j
@NotThreadSafe
public class ImmutableExample1 {
    private final static Integer a = 1;
    private final static String b = "2";
    private final  static Map<Integer,Integer> map = Maps.newHashMap();

    static {
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
    }

    public static void main(String[] args) {
        map.put(1,12);
        log.info("{}",map);
    }
}
