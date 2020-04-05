package com.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.signature.qual.SignatureBottom;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier 练习
 * 等待所有运动员全部准备好之后(运动员数量=屏障点),同时赛跑(并发)
 * Create by liangxifeng on 19-7-29
 */
@Slf4j
public class CyclicBarrierExample1 {
    //初始化屏障点=5
    private static CyclicBarrier barrier = new CyclicBarrier(5);

    /**
     * 输出结果:(注意看日志中的秒数)
     *  11:40:44.796 [pool-1-thread-1] INFO CyclicBarrierExample1 - 0 is ready
        11:40:45.791 [pool-1-thread-2] INFO CyclicBarrierExample1 - 1 is ready
        11:40:46.793 [pool-1-thread-3] INFO CyclicBarrierExample1 - 2 is ready
        11:40:47.794 [pool-1-thread-4] INFO CyclicBarrierExample1 - 3 is ready
        11:40:48.793 [pool-1-thread-5] INFO CyclicBarrierExample1 - 4 is ready
        11:40:48.793 [pool-1-thread-5] INFO CyclicBarrierExample1 - 4 continue
        11:40:48.793 [pool-1-thread-1] INFO CyclicBarrierExample1 - 0 continue
        11:40:48.793 [pool-1-thread-3] INFO CyclicBarrierExample1 - 2 continue
        11:40:48.793 [pool-1-thread-4] INFO CyclicBarrierExample1 - 3 continue
        11:40:48.793 [pool-1-thread-2] INFO CyclicBarrierExample1 - 1 continue
        11:40:49.793 [pool-1-thread-6] INFO CyclicBarrierExample1 - 5 is ready
        11:40:50.793 [pool-1-thread-1] INFO CyclicBarrierExample1 - 6 is ready
        11:40:51.793 [pool-1-thread-2] INFO CyclicBarrierExample1 - 7 is ready
        11:40:52.793 [pool-1-thread-4] INFO CyclicBarrierExample1 - 8 is ready
        11:40:53.793 [pool-1-thread-3] INFO CyclicBarrierExample1 - 9 is ready
        11:40:53.794 [pool-1-thread-3] INFO CyclicBarrierExample1 - 9 continue
        11:40:53.794 [pool-1-thread-6] INFO CyclicBarrierExample1 - 5 continue
        11:40:53.794 [pool-1-thread-4] INFO CyclicBarrierExample1 - 8 continue
        11:40:53.794 [pool-1-thread-1] INFO CyclicBarrierExample1 - 6 continue
        11:40:53.794 [pool-1-thread-2] INFO CyclicBarrierExample1 - 7 continue
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //初始化线程池
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            //每次调用前等待1秒
            Thread.sleep(1000);

            executor.execute(() -> {
                try {
                    race(threadNum);
                } catch (Exception e) {
                    log.error("exception",e);
                }
            });

        }
        executor.shutdown();

    }

    /**
     * 跑步方法,有5条跑道,调用barrier.await()等待5名运动员同时准备好之后
     * 同时进行赛跑(并发)
     * @param threadNum
     * @throws Exception
     */
    private static void race(int threadNum) throws Exception{
        Thread.sleep(1000);
        log.info("{} is ready",threadNum);
        barrier.await();
        log.info("{} continue",threadNum);
    }
}
