package com.concurrency.example.syncContainer;

import com.concurrency.annoations.NotThreadSafe;
import com.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Vector 实现了List接口, 线程安全测试
 * Create by liangxifeng on 19-7-17
 */
@Slf4j
@ThreadSafe
public class VectorExample1 {
    //请求总数
    public static int clientTotal = 5000;
    //同时并执行的线程数
    public static int threadTotal = 200;
    public static int count = 0;

    private static Vector<Integer> list = new Vector<>();

    public static void main(String[] args) throws InterruptedException {
        //定义线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //定义信号量
        final Semaphore semaphore = new Semaphore(threadTotal);
        //定义计数器闭锁
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire(); //判断过当前进程是否允许被执行
                    update(count);
                    semaphore.release(); //执行完毕后释放当前这个进程
                } catch (InterruptedException e) {
                    log.error("exception",e);
                    e.printStackTrace();
                }
                //进程执行结束后,计数-1
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        executorService.shutdown();
        //输出结果=5000,说明Vector是线程安全的.
        log.info("list size: {}",list.size());

    }
    private static void update(int i) {
        list.add(i);
    }
}
