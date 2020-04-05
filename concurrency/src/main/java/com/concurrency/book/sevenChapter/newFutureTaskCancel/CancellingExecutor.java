package com.concurrency.book.sevenChapter.newFutureTaskCancel;

import java.util.concurrent.*;

/**
 * 使用newTaskfor封装任务中非标准取消,
 * 继承线程池ThreadPoolExecutor,重写newTaskFor方法,
 * 返回存在自定义cancel()的RunnableFuture,
 * 目的:不仅可以安全的中断方法, 还可以通过自定义的cancel()关闭SocketIO
 * Create by liangxifeng on 19-9-17
 */
public class CancellingExecutor extends ThreadPoolExecutor {
    public CancellingExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        //如果是自定义的Callable则返回自定义的callable.newTask()
        //自定义的callable.newTask()返回自定义取消任务方法cancel()的FutureTask
        if(callable instanceof CancellableTask) {
            return ((CancellableTask) callable).newTask();
        } else
        {
            return super.newTaskFor(callable);
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ArrayBlockingQueue queue = new ArrayBlockingQueue(20);
        //创建自定义的线程池
        CancellingExecutor executor = new CancellingExecutor(10,
                20,2000,TimeUnit.MILLISECONDS,queue);

        //创建自定义Callable
        SocketUsingTask task = new SocketUsingTask();
        Future res = executor.newTaskFor(task);
        //使用自定义的取消任务
        res.cancel(true);
        System.out.println(res.get());
    }
}
