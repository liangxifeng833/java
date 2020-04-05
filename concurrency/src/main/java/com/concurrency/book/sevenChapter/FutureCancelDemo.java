package com.concurrency.book.sevenChapter;

import java.util.concurrent.*;

/**
 * 使用Future取消任务demi
 * Create by liangxifeng on 19-9-17
 */
public class FutureCancelDemo {
    static ExecutorService taskExec = Executors.newCachedThreadPool();

    public static void timedRun(Runnable r,
                                long timeout, TimeUnit unit) throws InterruptedException {
        Future<?> task = taskExec.submit(r);
        try {
            task.get(timeout,unit);
        } catch (ExecutionException e) {
            System.out.println("task中抛出异常,在这里捕获");
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println("已超时,该任务被取消");
            //e.printStackTrace();
        } finally {
            task.cancel(true);//最后取消任务
        }
    }

    /**
     * 输出:
     *   已超时,该任务被取消
     *   睡眠异常
     *   hello
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        FutureCancelDemo.timedRun(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("睡眠异常");
                //e.printStackTrace();
            }
            System.out.println("hello");
        },1000,TimeUnit.MILLISECONDS);
    }

}
