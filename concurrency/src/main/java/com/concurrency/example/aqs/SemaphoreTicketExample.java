package com.concurrency.example.aqs;

import java.util.concurrent.Semaphore;

/**
 * Description: Semaphore 模拟只有５个窗口并行卖火车票的场景
 * Create by liangxifeng on 19-7-29
 */
public class SemaphoreTicketExample {
    public static void main(String[] args) {
        Semaphore windows = new Semaphore(5);  // 声明5个窗口

        //总共有8个客户同时抢票
        for (int i = 0; i < 8; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        windows.acquire();  // 占用窗口
                        System.out.println(Thread.currentThread().getName() + ": 开始买票");
                        sleep(2000);  // 睡2秒，模拟买票流程
                        System.out.println(Thread.currentThread().getName() + ": 购票成功");
                        windows.release();  // 释放窗口
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}
