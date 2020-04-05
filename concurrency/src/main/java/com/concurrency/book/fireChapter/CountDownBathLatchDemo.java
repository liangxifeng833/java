package com.concurrency.book.fireChapter;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁CountDownLatch练习
 *
 * 以下程序有两个闭锁启动门（startGate）和结束门闭锁(endGate)
 * 启动门闭锁：保证多个线程全部准备就绪后一起执行
 * 结束门闭锁：保证多个线程全部结束后，主线程统计执行时间
 * Create by liangxifeng on 19-9-7
 */
public class CountDownBathLatchDemo {

    public long timeTask(int nThreds, final Runnable task) throws InterruptedException {
        //启动门闭锁，计数器=1
        final CountDownLatch startGate = new CountDownLatch(1);
        //结束门闭锁，计数器=线程的数量
        final CountDownLatch endGate = new CountDownLatch(nThreds);

        for (int i=0; i<nThreds; i++) {
            Thread t = new Thread() {
                public void run() {
                    try {
                        //启动门闭锁等待所有线程同时开始运行
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            //每个线程执行完毕后,结束门闭锁计数器-1
                            endGate.countDown();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            t.start();
        }
        long start = System.currentTimeMillis();
        startGate.countDown();//启动门计数器-1：保证多个线程同时执行
        endGate.await();//结束门闭锁等待所有线程执行完毕后，主线程main继续执行
        long end = System.currentTimeMillis();
        return end-start; //返回执行时间
    }

    public static void main(String[] args) {
        CountDownBathLatchDemo countDownBathLatchDemo = new CountDownBathLatchDemo();
        try {
            long processTime = countDownBathLatchDemo.timeTask(2,()->{
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("hello");
            });
            System.out.println("处理时间="+processTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
