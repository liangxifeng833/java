package com.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * JUC - Semaphore 练习
 * Create by liangxifeng on 19-7-28
 */
@Slf4j
public class SemaphoreExample1 {
    //线程总数
    private final static int threadCount = 10;

    public static void main(String[] args) throws Exception{
        //定义线程池
        ExecutorService exec = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(2);

        //循环执行10个线程
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute( ()->{
                try {
                    semaphore.acquire(); //获取一个许可
                    test(threadNum);
                    semaphore.release(); //释放一个许可
                } catch (Exception e) {
                    log.error("exception",e);
                }
            });
        }

        //关闭线程池
        exec.shutdown();
    }

    private static void test(int threadNum) throws Exception {
        log.info("{}",threadNum);
        Thread.sleep(2000);
    }
}
