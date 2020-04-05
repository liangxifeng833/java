package com.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CyclicBarrier 练习
 * 等待所有运动员全部准备好之后(运动员数量=屏障点),同时赛跑(并发)
 * 如果超出等待时间, 运动员数量<屏障点的时候,在超时时间内所有准备好的运动员同时赛跑(并发)
 * Create by liangxifeng on 19-7-29
 */
@Slf4j
public class CyclicBarrierExample2 {
    private static CyclicBarrier barrier = new CyclicBarrier(5);

    /**
     * 输出结果:
     11:49:34.806 [pool-1-thread-1] INFO CyclicBarrierExample2 - 0 is ready
     11:49:35.804 [pool-1-thread-2] INFO CyclicBarrierExample2 - 1 is ready
     11:49:36.806 [pool-1-thread-3] INFO CyclicBarrierExample2 - 2 is ready
     11:49:36.811 [pool-1-thread-1] ERROR CyclicBarrierExample2 - await exception: 异常抛出
     11:49:36.811 [pool-1-thread-2] ERROR CyclicBarrierExample2 - await exception: 异常抛出
     11:49:36.811 [pool-1-thread-3] ERROR CyclicBarrierExample2 - await exception: 异常抛出
     11:49:36.811 [pool-1-thread-1] INFO CyclicBarrierExample2 - 0 continue
     11:49:36.811 [pool-1-thread-2] INFO CyclicBarrierExample2 - 1 continue
     11:49:36.811 [pool-1-thread-3] INFO CyclicBarrierExample2 - 2 continue
     11:49:37.806 [pool-1-thread-4] INFO CyclicBarrierExample2 - 3 is ready
     11:49:37.806 [pool-1-thread-4] ERROR CyclicBarrierExample2 - await exception: 异常抛出
     11:49:37.806 [pool-1-thread-4] INFO CyclicBarrierExample2 - 3 continue
     11:49:38.805 [pool-1-thread-3] INFO CyclicBarrierExample2 - 4 is ready
     11:49:38.805 [pool-1-thread-3] ERROR CyclicBarrierExample2 - await exception: 异常抛出
     11:49:38.805 [pool-1-thread-3] INFO CyclicBarrierExample2 - 4 continue
     11:49:39.805 [pool-1-thread-4] INFO CyclicBarrierExample2 - 5 is ready
     11:49:39.805 [pool-1-thread-4] ERROR CyclicBarrierExample2 - await exception: 异常抛出
     11:49:39.806 [pool-1-thread-4] INFO CyclicBarrierExample2 - 5 continue
     11:49:40.805 [pool-1-thread-3] INFO CyclicBarrierExample2 - 6 is ready
     11:49:40.806 [pool-1-thread-3] ERROR CyclicBarrierExample2 - await exception: 异常抛出
     11:49:40.806 [pool-1-thread-3] INFO CyclicBarrierExample2 - 6 continue
     11:49:41.806 [pool-1-thread-4] INFO CyclicBarrierExample2 - 7 is ready
     11:49:41.806 [pool-1-thread-4] ERROR CyclicBarrierExample2 - await exception: 异常抛出
     11:49:41.806 [pool-1-thread-4] INFO CyclicBarrierExample2 - 7 continue
     11:49:42.806 [pool-1-thread-3] INFO CyclicBarrierExample2 - 8 is ready
     11:49:42.806 [pool-1-thread-3] ERROR CyclicBarrierExample2 - await exception: 异常抛出
     11:49:42.806 [pool-1-thread-3] INFO CyclicBarrierExample2 - 8 continue
     11:49:43.806 [pool-1-thread-4] INFO CyclicBarrierExample2 - 9 is ready
     11:49:43.806 [pool-1-thread-4] ERROR CyclicBarrierExample2 - await exception: 异常抛出
     11:49:43.806 [pool-1-thread-4] INFO CyclicBarrierExample2 - 9 continue
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //初始化线程池
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
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
     * 如果超出等待时间2秒, 即使运动员数量<屏障点的时候,在2秒内所有准备好的运动员同时赛跑(并发)
     * @param threadNum
     * @throws Exception
     */
    private static void race(int threadNum) throws Exception{
        Thread.sleep(1000);
        log.info("{} is ready",threadNum);
        try {
            barrier.await(2000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            log.error("await exception: {}","异常抛出");
        }

        log.info("{} continue",threadNum);
    }
}
