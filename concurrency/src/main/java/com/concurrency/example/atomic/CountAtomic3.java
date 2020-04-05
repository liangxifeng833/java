package com.concurrency.example.atomic;


import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.atomic.AtomicReference;

/**
 * AtomicReference 原子性操作
 * Create by liangxifeng on 19-7-17
 */
@Slf4j
public class CountAtomic3 {
  private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) {
        //if (count值==0) 更新为2
        count.compareAndSet(0,2); // 2
        //if (count值==0) 更新为1
        count.compareAndSet(0,1); // no
        //if (count值==1) 更新为3
        count.compareAndSet(1,3); // no
        //if (count值==2) 更新为4
        count.compareAndSet(2,4); // 4
        //if (count值==3) 更新为5
        count.compareAndSet(3,5);
        log.info("count:{}",count.get());
    }
}
