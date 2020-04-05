package com.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * JUC - CountDownLatch await等待超时，练习
 * Create by liangxifeng on 19-7-28
 */
@Slf4j
public class CountDownLatchExample2 {
    //线程总数
    private final static int threadCount = 10;

    /**
     * 输出结果　CountDownLatchExample2 - finish
     * 　　　　　CountDownLatchExample2 - 2
     *         CountDownLatchExample2 - 4....
     * @param args
     * @throws Exception
     */

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

        //等待所有线程执行结束,10毫秒后如果以上所有子线程依然没有执行完成，
        // 则主线程不在等待继续执行，输出一下finish（注意：子线程并没有终止）
        countDownLatch.await(10, TimeUnit.MILLISECONDS);
        log.info("finish");
        //关闭线程池
        exec.shutdown();


    }

    private static void test(int threadNum) throws Exception {
        Thread.sleep(1000);
        log.info("{}",threadNum);
    }
}
