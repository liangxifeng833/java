package com.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CyclicBarrier 练习
 * 等待所有运动员全部准备好之后(运动员数量=屏障点),先执行一个动作后, 多个线程再同时赛跑(并发)
 * Create by liangxifeng on 19-7-29
 */
@Slf4j
public class CyclicBarrierExample3 {
    // 构造器第二个参数是一个Runalbe函数式接口,当5个线程全部准备好之后,先执行该函数后并发
    private static CyclicBarrier barrier = new CyclicBarrier(5,()->{
        System.out.println("5个运动员全部准备好了,可以金进行赛跑了");
    });

    /**
     * 输出结果:
     10:08:22.107 [pool-1-thread-1] CyclicBarrierExample3 - 0 is ready
     10:08:23.103 [pool-1-thread-2] CyclicBarrierExample3 - 1 is ready
     10:08:24.104 [pool-1-thread-3] CyclicBarrierExample3 - 2 is ready
     10:08:25.104 [pool-1-thread-4] CyclicBarrierExample3 - 3 is ready
     10:08:26.105 [pool-1-thread-5] CyclicBarrierExample3 - 4 is ready
     5个运动员全部准备好了,可以金进行赛跑了
     10:08:26.105 [pool-1-thread-5] CyclicBarrierExample3 - 4 continue
     10:08:26.105 [pool-1-thread-1] CyclicBarrierExample3 - 0 continue
     10:08:26.105 [pool-1-thread-2] CyclicBarrierExample3 - 1 continue
     10:08:26.105 [pool-1-thread-3] CyclicBarrierExample3 - 2 continue
     10:08:26.105 [pool-1-thread-4] CyclicBarrierExample3 - 3 continue
     10:08:27.105 [pool-1-thread-6] CyclicBarrierExample3 - 5 is ready
     10:08:28.105 [pool-1-thread-1] CyclicBarrierExample3 - 6 is ready
     10:08:29.105 [pool-1-thread-2] CyclicBarrierExample3 - 7 is ready
     10:08:30.105 [pool-1-thread-4] CyclicBarrierExample3 - 8 is ready
     10:08:31.106 [pool-1-thread-5] CyclicBarrierExample3 - 9 is ready
     5个运动员全部准备好了,可以金进行赛跑了
     10:08:31.106 [pool-1-thread-5] CyclicBarrierExample3 - 9 continue
     10:08:31.106 [pool-1-thread-6] CyclicBarrierExample3 - 5 continue
     10:08:31.106 [pool-1-thread-1] CyclicBarrierExample3 - 6 continue
     10:08:31.107 [pool-1-thread-4] CyclicBarrierExample3 - 8 continue
     10:08:31.106 [pool-1-thread-2] CyclicBarrierExample3 - 7 continue
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
            barrier.await();
        } catch (Exception e) {
            log.error("await exception: {}","异常抛出");
        }

        log.info("{} continue",threadNum);
    }
}
