package com.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 通过Callable产生线程的结果，通过Future获取线程执行的结果　实例
 * Create by liangxifeng on 19-7-31
 */
@Slf4j
public class FutureExample {

    static class MyCallalbe implements Callable<String> {
        @Override
        public String call() throws Exception {
            log.info("do something in callalbe");
            //执行任务需要5秒
            Thread.sleep(5000);
            return "Done";
        }
    }
    /**
     * 输出结果：
     17:48:14.626 [pool-1-thread-1] FutureExample - do something in callalbe
     17:48:14.625 [main] FutureExample - do something in main
     17:48:19.630 [main] FutureExample - result:Done
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //创建一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //创建有返回值的任务
        Callable<String> cl = new MyCallalbe();
        //执行任务并获取Future对象 ( 将cl提交到线程池中进行处理 )
        Future<String> future = executorService.submit(cl);
        log.info("do something in main");
        Thread.sleep(1000);
        //输出结果,通过future.get()获取Callable中保存的结果
        String result = future.get();
        log.info("result:{}",result);
        //关闭线程池
        executorService.shutdown();

    }
}
