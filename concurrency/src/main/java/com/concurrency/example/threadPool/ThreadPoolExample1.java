package com.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池测试　Executors.newCachedThreadPool
 * Create by liangxifeng on 19-8-1
 */
@Slf4j
public class ThreadPoolExample1 {

    /**
     * 没有关闭
     * @param args
     * @throws Exception
     */
    public static void main (String[] args) throws Exception {
        //创建一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //向线程池中放入10个任务
        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("task:{}", index);
                }
            });
        }
        //此处没有关闭线程，默认60秒后，自动关闭，释放资源．
        //executorService.shutdown();
    }
}
