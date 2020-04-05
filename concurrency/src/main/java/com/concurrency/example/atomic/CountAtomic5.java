package com.concurrency.example.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * AtomicBoolean 原子性操作boolean值, 让一段代码值执行一次操作
 * Create by liangxifeng on 19-7-17
 */
@Slf4j
public class CountAtomic5 {
    //请求总数
    public static int clientTotal = 5000;
    //同时并执行的线程数
    public static int threadTotal = 200;
    //声明AtomicBoolean形变量 isHappened,默认值=false
    private static AtomicBoolean isHappened = new AtomicBoolean(false);
    public static void main(String[] args) throws Exception {
        //定义线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //定义信号量
        final Semaphore semaphore = new Semaphore(threadTotal);
        //定义计数器闭锁
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire(); //判断过当前进程是否允许被执行
                    test();
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
        log.info("isHappened:{}",isHappened.get());
    }

    private static void test() {
        //以上5000次请求,这里只输出一次execute
        if (isHappened.compareAndSet(false,true)) {
            log.info("execute");
        }
    }
}
