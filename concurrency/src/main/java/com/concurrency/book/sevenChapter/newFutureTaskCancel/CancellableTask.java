package com.concurrency.book.sevenChapter.newFutureTaskCancel;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

/**
 * 继承Callable接口,自定义cancel()和newTask()方法
 * Create by liangxifeng on 19-9-17
 */
public interface CancellableTask<T> extends Callable<T> {
    void cancel();
    RunnableFuture<T> newTask();
}
