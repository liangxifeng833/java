package com.concurrency.example.immutable;

import com.concurrency.annoations.NotThreadSafe;
import com.concurrency.annoations.ThreadSafe;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;

/**
 * guava的不可变list => ImmutableList
 * guava的不可变set => ImmutableSet
 * guava的不可便map => ImmutableMap
 * Create by liangxifeng on 19-7-18
 */
@Slf4j
@ThreadSafe
public class ImmutableExample3 {
    //不可变list -> ImmutableList
    private final static ImmutableList<Integer> list = ImmutableList.of(1,2,3,4,5);
    //不可变set -> ImmutableSet
    private final static ImmutableSet set = ImmutableSet.copyOf(list);

    //不可变map -> ImmutableMap 以下是两种创建方式
    private final static ImmutableMap<String,String> map = ImmutableMap.of("key1","value1","key2","value2");
    private final static ImmutableMap<String,String> map2 = ImmutableMap.<String,String>builder()
            .put("key1","value1")
            .put("key2", "value2").build();

    public static void main(String[] args) {
        log.info("{}",set);
    }
}
