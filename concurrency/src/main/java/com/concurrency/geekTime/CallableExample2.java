package com.concurrency.geekTime;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Description: Callable+Future/FutureTask可以获取多线程运行的结果
 * Create by liangxifeng on 19-8-20
 */
public class CallableExample2 implements Callable<Integer> {

    /**
     * 输出结果：
         有返回值的线程 0
         有返回值的线程 2
         有返回值的线程 4
         有返回值的线程 6
         有返回值的线程 8
         子线程的返回值10
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建任务
        CallableExample2 ce = new CallableExample2();
        FutureTask<Integer> ft = new FutureTask<>(ce);
        //将任务放到线程中并启动
        new Thread(ft,"有返回值的线程").start();
        System.out.println("子线程的返回值" + ft.get());

    }

    @Override
    public Integer call() throws Exception {
        int i;
        for (i = 0; i < 10; i += 2) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
        return i;
    }
}
