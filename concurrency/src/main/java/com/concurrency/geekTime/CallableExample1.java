package com.concurrency.geekTime;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Description: Callable+Future/FutureTask可以获取多线程运行的结果
 * Callable接口可以编写多线程程序，采用Thread.start()启动
 * Create by liangxifeng on 19-8-20
 */
public class CallableExample1 {


    public static void main(String[] args) throws Exception {
        //创建一个callable
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(2000);
                return new Random().nextInt(10);
            }
        };
        //使用lamda表达式方式创建callable
        Callable<Integer> callable1 = ()->{
            Thread.sleep(2000);
            return new Random().nextInt(10);
        };

        //创建futureTask,用来执行执行线程并可以获取线程的返回结果
        //FutureTask实现了 Runnable 和 Future 接口,所以既可以获执行Runalbe有可以通过Future获取线程执行的结果
        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        //使用线程启动futureTask
        new Thread(futureTask).start();

        Thread.sleep(1000);
        System.out.println("hello begin");
        //取消线程的执行使用FutureTask.cancel(boolean)
        //boolean = true并且任务正在运行，那么这个任务将被取消
        //boolean = false并且任务正在运行，那么这个任务将不会被取消
        //futureTask.cancel(true);

        //isDone()用来判断任务是否已结束，输出false
        System.out.println(futureTask.isDone());

        //如果线程没有取消
        if(!futureTask.isCancelled()) {
            //等待并获取线程执行结果，输出0-10之间的随机数
            System.out.println(futureTask.get());
            //输出true，代表任务已经结束
            System.out.println(futureTask.isDone());
            System.out.println("hello end");
        }
    }
}
