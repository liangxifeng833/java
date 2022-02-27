package com.concurrency;

import com.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程死锁demo
 * Create by liangxifeng on 2021-08-09
 */
@Slf4j
@NotThreadSafe
public class DeadLockDemo {
    private static Lock lock1 = new ReentrantLock();
    private static Lock lock2 = new ReentrantLock();

    public static void deadthLock(){
        Thread t1 = new Thread(){
            @Override
            public void  run() {
                try{
                    lock1.lock();
                    System.out.println(Thread.currentThread().getName()+" get the Lock1");
                    Thread.sleep(1000);

                    lock2.lock();
                    System.out.println(Thread.currentThread().getName()+" get the Lock2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t2 = new Thread(){
            @Override
            public void  run() {
                try{
                    lock2.lock();
                    System.out.println(Thread.currentThread().getName()+" get the lock2");
                    Thread.sleep(1000);

                    lock1.lock();
                    System.out.println(Thread.currentThread().getName()+" get the lock1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        //设置线程名字，方便分析堆栈信息
        t1.setName("mythread-zhangsan");
        t2.setName("mythread-lisi");
        t1.start();
        t2.start();
    }
    public static void main(String[] args) {
        deadthLock();
    }
}
