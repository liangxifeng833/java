package com.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description: 使用同步锁修饰静态方法和类
 * Create by liangxifeng on 19-7-18
 */
@Slf4j
public class SynchronizedExampe2 {

    //修饰一个代码块
    public static  void test1(String objType) {
        synchronized (SynchronizedExampe2.class) {
            for (int i = 0; i < 10; i++) {
                log.info("test1 - {} - {}",objType,i);
            }
        }
    }

    //修饰一个静态方法,作用范围为整个方法
    public static synchronized void test2(String objType) {
        for (int i = 0; i < 10; i++) {
            log.info("test2 - {} - {}",objType,i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExampe2 exampe1 = new SynchronizedExampe2();
        SynchronizedExampe2 exampe2 = new SynchronizedExampe2();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute( () -> {
            exampe1.test1("example1");
        });
        executorService.execute( () -> {
            exampe2.test1("example2");
        });
    }
}
