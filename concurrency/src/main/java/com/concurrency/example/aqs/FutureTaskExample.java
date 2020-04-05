package com.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * FutureTask作为Runable被线程执行
 * 并通过FutureTask获取对应线程执行的结果
 * Create by liangxifeng on 19-7-31
 */
@Slf4j
public class FutureTaskExample {
    /**
     * 输出结果：
     18:29:58.083 [main] FutureTaskExample - do something in main
     18:29:58.083 [Thread-0] FutureTaskExample - do something in callalbe
     18:30:03.088 [main] FutureTaskExample - result:Done
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //创建一个FutureTask
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("do something in callalbe");
                Thread.sleep(5000);
                return "Done";
            }
        });
        //FutureTask作为runalbe被线程执行
        new Thread(futureTask).start();
        log.info("do something in main");
        Thread.sleep(1000);
        //通哦过FutureTask.get()获取线程执行的结果
        String result = futureTask.get();
        log.info("result:{}",result);

    }
}
