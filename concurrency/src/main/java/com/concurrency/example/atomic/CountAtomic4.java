package com.concurrency.example.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * AtomicIntegerFieldUpdater 原子性改变指定对象中的属性状态
 * Create by liangxifeng on 19-7-17
 */
@Slf4j
public class CountAtomic4 {
    private static AtomicIntegerFieldUpdater<CountAtomic4> updater = AtomicIntegerFieldUpdater.newUpdater(
            CountAtomic4.class, "count");

    @lombok.Getter
    public volatile int count = 100; //这里的count变量必须标识为volatile并且为非static

    public static void main(String[] args) {
        CountAtomic4 countAtomic4 = new CountAtomic4();
        //if(countAtomic4.count==100) 则将count修改为120
        if (updater.compareAndSet(countAtomic4, 100, 120)) {
            log.info("update success1, {}", countAtomic4.getCount());
        }

        if (updater.compareAndSet(countAtomic4, 100, 150)) {
            log.info("update success2,{}", countAtomic4.getCount());
        } else {
            log.info("update failed2");
        }
    }
}
