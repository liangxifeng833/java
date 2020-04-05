package com.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池测试　Executors.newFixedThreadPool
 * Create by liangxifeng on 19-8-1
 */
@Slf4j
public class ThreadPoolExample2 {

    public static void main(String[] args) {
        //创建一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        //向线程池中放入10个任务
        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    log.info("task:{}", index);
                }
            });
        }
        executorService.shutdown();
    }
}
