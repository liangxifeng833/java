package com.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * JUC - Semaphore 练习
 * 当`acquire()`申请获得的许可数量 = Semaphore的最大许可数时,
 * 其他线程不等待, 直接终止执行.
 * Create by liangxifeng on 19-7-28
 */
@Slf4j
public class SemaphoreExample2 {
    //线程总数
    private final static int threadCount = 10;

    /**
     * 输出结果:
     * SemaphoreExample2 - 1
     * SemaphoreExample2 - 0
     * 代表只有两个线程执行了,其他线程全部终止执行
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        //定义线程池
        ExecutorService exec = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(3);

        //循环执行10个线程
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute( ()->{
                try {
                    if(semaphore.tryAcquire()) { //尝试获取许可
                        test(threadNum);
                        semaphore.release(); //释放一个许可
                    }
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
