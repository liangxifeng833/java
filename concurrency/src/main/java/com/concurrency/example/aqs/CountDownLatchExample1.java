package com.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * JUC - CountDownLatch 练习
 * Create by liangxifeng on 19-7-28
 */
@Slf4j
public class CountDownLatchExample1 {
    //线程总数
    private final static int threadCount = 10;

    public static void main(String[] args) throws Exception{
        //定义线程池
        ExecutorService exec = Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        //循环执行10个线程
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute( ()->{
                try {
                    test(threadNum);
                } catch (Exception e) {
                    log.error("exception",e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        //等待所有线程执行结束
        countDownLatch.await();
        //关闭线程池
        exec.shutdown();
        log.info("finish");
    }

    private static void test(int threadNum) throws Exception {
        log.info("{}",threadNum);
        Thread.sleep(1000);
    }
}
