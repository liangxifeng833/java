package com.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * JUC - Semaphore 练习
 * 当`acquire()`申请获得的许可数量 = Semaphore的最大许可数时,
 * 其他线程不等待, 直接终止执行,有超时限制的尝试获取许可
 * Create by liangxifeng on 19-7-28
 */
@Slf4j
public class SemaphoreExample3 {
    //线程总数
    private final static int threadCount = 100;

    /**
     * 输出的结果并不是:
     *  SemaphoreExample2 - 1
     *  SemaphoreExample2 - 0
     * 而是:
     *  10:38:36.839 [pool-1-thread-2] SemaphoreExample3 - 1
        10:38:36.836 [pool-1-thread-1] SemaphoreExample3 - 0
        10:38:38.843 [pool-1-thread-3] SemaphoreExample3 - 2
        10:38:38.843 [pool-1-thread-4] SemaphoreExample3 - 3
        10:38:40.843 [pool-1-thread-5] SemaphoreExample3 - 4
        10:38:40.844 [pool-1-thread-6] SemaphoreExample3 - 5
     * 代表只有两个线程执行了,其他线程全部终止执行
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        //定义线程池
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(2);

        //循环执行10个线程
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute( ()->{
                try {
                    //5秒内semaphore.release()释放的许可,其他线程依然可以申请,
                    //也就是说5秒内的线程没有丢弃不执行.
                    if(semaphore.tryAcquire(5000, TimeUnit.MILLISECONDS)) {
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
