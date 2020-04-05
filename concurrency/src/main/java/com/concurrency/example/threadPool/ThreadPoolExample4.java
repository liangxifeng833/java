package com.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程池测试　Executors.newScheduledThreadPool
 * Create by liangxifeng on 19-8-1
 */
@Slf4j
public class ThreadPoolExample4 {

    public static void main(String[] args) {
        //创建一个线程池
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);
        //延迟３秒后执行
//        executorService.schedule(new Runnable() {
//            @Override
//            public void run() {
//                log.info("task:{}", "执行任务");
//            }
//        }, 3, TimeUnit.SECONDS);
//
//        //任务创建后延迟1秒执行，每隔３秒执行一次任务
//        executorService.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                log.info("task:{}", "执行任务");
//            }
//        },1,3,TimeUnit.SECONDS);
        //executorService.shutdown();

        //使用Timer定时器，每隔5秒执行一次
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.warn("timer run");
            }
        },new Date(),5000);


    }
}
