package com.concurrency.geekTime;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Description: myFirst
 * Create by liangxifeng on 19-8-27
 */
public class ThreadLocalExample1 {
    static final AtomicLong nextId = new AtomicLong(0);
    //定义ThreadLocal变量
    static final ThreadLocal<Long> tl = ThreadLocal.withInitial(
            ()->nextId.getAndIncrement()
    );

    //此方法会为每一个线程分配一个唯一的id
    static long get() {
        return tl.get();
    }

    public static void main(String[] args) {
        //同一个线程调用ThreadLocal变量,每次返回相同的值,输出：
        //Thread-0myid=0
        //Thread-0myid=0
        //Thread-0myid=0
        new Thread(()->{
            for (int i=0; i<3; i++) {
                long myId = ThreadLocalExample1.get();
                System.out.println(Thread.currentThread().getName()+"myid="+myId);
            }
        }).start();

        //不同线程调用ThreadLocal变量，每次返回不同值,输出结果：
        //duo xiancheng myidA=1
        //duo xiancheng myidB=2
        //duo xiancheng myidC=3
        new Thread(()-> System.out.println("duo xiancheng myidA="+ThreadLocalExample1.get())).start();
        new Thread(()-> System.out.println("duo xiancheng myidB="+ThreadLocalExample1.get())).start();
        new Thread(()-> System.out.println("duo xiancheng myidC="+ThreadLocalExample1.get())).start();

    }
}
