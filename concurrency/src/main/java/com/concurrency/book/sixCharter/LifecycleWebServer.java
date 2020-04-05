package com.concurrency.book.sixCharter;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/**
 * 支持关闭操作的web服务器
 * Create by liangxifeng on 19-9-12
 */
public class LifecycleWebServer {
    private final ExecutorService exec = Executors.newCachedThreadPool();
    public void start() throws IOException {
        ServerSocket socket = new ServerSocket(8081);
        //如果没有关闭线程池的处理情况
        while (!exec.isShutdown()) {
            try {
                final Socket connection = socket.accept();
                exec.execute(new Runnable() {
                    @Override
                    public void run() {
                        //curl loclhost:8098会输出: Socket[addr=/0:0:0:0:0:0:0:1,port=34743,localport=8099]
                        System.out.println("处理请求:"+connection.toString());
                    }
                });
            //线程池关闭后，再次想线程池提交任务的处理
            } catch (RejectedExecutionException e) {
                if (!exec.isShutdown()) {
                    System.out.println("线程池已关闭，不可在提交任务");
                }
            }
        }
    }
}
