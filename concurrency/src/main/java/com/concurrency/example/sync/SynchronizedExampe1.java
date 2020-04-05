package com.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description: 使用同步锁修饰方法和代码快
 * Create by liangxifeng on 19-7-18
 */
@Slf4j
public class SynchronizedExampe1 {

    //修饰一个代码快
    public void test1(String objType) {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("test1 - {} - {}",objType,i);
            }
        }
    }

    //修饰一个方法,作用范围为整个方法
    public synchronized void test2(String objType) {
        for (int i = 0; i < 10; i++) {
            log.info("test2 - {} - {}",objType,i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExampe1 exampe1 = new SynchronizedExampe1();
        SynchronizedExampe1 exampe2 = new SynchronizedExampe1();
        //创建线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //启动第一个线程
        executorService.execute( () -> {
            exampe1.test1("example1");
        });
        //启动第二个线程
        executorService.execute( () -> {
            exampe2.test1("example2");
        });
    }
}
