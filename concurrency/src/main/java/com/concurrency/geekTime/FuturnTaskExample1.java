package com.concurrency.geekTime;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * FutureTask工具类实现了 Runnable 和 Future接口
 * 可以将 FutureTask 对象作为任务提交给 ThreadPoolExecutor执行　也可以直接被 Thread 执行
 *　因为实现了 Future 接口，所以也能用来获得任务的执行结果
 *
 */
public class FuturnTaskExample1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建FuturnTask
        FutureTask<Integer> futureTask = new FutureTask<>(()->1+2);
        //创建线程池
        ExecutorService es = Executors.newFixedThreadPool(1);
        //FutureTask作为Runnable任务提交到线程池
        es.submit(futureTask);
        //获取计算结果,FutureTask作为Funtrue获取线程执行结果
        Integer res = futureTask.get();
        System.out.println("计算结果="+res); //输出：3

        //通过Thread的方式执行FutrueTask
        /*
        Thread T1 = new Thread(futureTask);
        T1.start();
        Integer res2 = futureTask.get();
        */
    }
}
