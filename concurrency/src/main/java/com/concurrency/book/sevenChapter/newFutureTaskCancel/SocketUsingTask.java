package com.concurrency.book.sevenChapter.newFutureTaskCancel;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

/**
 * socket监听程序任务Callable实现自定义接口Callable
 * Create by liangxifeng on 19-9-17
 */
public class SocketUsingTask<T> implements CancellableTask<T> {
    private Socket socket;
    protected synchronized void setSocket(Socket s) {
        socket = s;
    }

    /**
     * 自定义取消方法,关闭socket服务
     */
    public synchronized void cancel() {
        try {
            if (socket != null ) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * FutureTask是RunnableFuture的实现类
     * @return
     */
    public RunnableFuture<T> newTask() {
        return new FutureTask<T>(this) {
            public boolean cancel(boolean mayInterruptIfRunning) {
                try {
                    SocketUsingTask.this.cancel();
                } finally {
                    return super.cancel(mayInterruptIfRunning);
                }

            }
        };
    }

    @Override
    public T call() throws Exception {
        System.out.println("socket程序处理逻辑...");
        String a = "socket程序处理逻辑...";
        return (T)a;
    }
}