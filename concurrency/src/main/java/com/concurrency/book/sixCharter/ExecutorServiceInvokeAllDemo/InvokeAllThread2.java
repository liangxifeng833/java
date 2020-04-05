package com.concurrency.book.sixCharter.ExecutorServiceInvokeAllDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 测试InvokeAll批量提交任务集
 * 不控制超时
 * Create by liangxifeng on 19-9-16
 */
public class InvokeAllThread2 {
    public static void testInvakeAll() throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(10);

        List<Callable<Integer>> tasks = new ArrayList<Callable<Integer>>();
        Callable<Integer> task = null;
        for (int i = 0; i < 5; i++) {
            task = new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int ran = new Random().nextInt(1000);
                    Thread.sleep(ran);
                    System.out.println(Thread.currentThread().getName()
                            + " 休息了 " + ran);
                    return ran;
                }
            };
            tasks.add(task);
        }

        long s = System.currentTimeMillis();

        List<Future<Integer>> results = exec.invokeAll(tasks);

        System.out.println("执行任务消耗了 ：" + (System.currentTimeMillis() - s)
                + "毫秒");

        for (int i = 0; i < results.size(); i++) {
            try {
                System.out.println("计算结果="+results.get(i).get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        exec.shutdown();
    }

    /**
     * 输出:
     pool-1-thread-3 休息了 96
     pool-1-thread-2 休息了 536
     pool-1-thread-4 休息了 651
     pool-1-thread-5 休息了 661
     pool-1-thread-1 休息了 737
     执行任务消耗了 ：740毫秒
     计算结果=737
     计算结果=536
     计算结果=96
     计算结果=651
     计算结果=661
     * @param args
     */
    public static void main(String[] args) {
        try {
            InvokeAllThread2.testInvakeAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
