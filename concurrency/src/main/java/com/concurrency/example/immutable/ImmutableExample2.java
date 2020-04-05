package com.concurrency.example.immutable;

import com.concurrency.annoations.NotThreadSafe;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * guava的unmodifiableMap为不可修改map
 * Create by liangxifeng on 19-7-18
 */
@Slf4j
@NotThreadSafe
public class ImmutableExample2 {
    private final static Integer a = 1;
    private final static String b = "2";
    private  static Map<Integer,Integer> map = Maps.newHashMap();

    static {
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        log.info("{}",map);
    }
}
