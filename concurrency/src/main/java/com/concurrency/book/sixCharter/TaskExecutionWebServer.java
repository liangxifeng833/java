package com.concurrency.book.sixCharter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 基于线程池的web服务器
 * 创建可以容纳100个请求的线程池,
 * 避免无限制的创建线程（为每个请求创建一个线程）
 * Create by liangxifeng on 19-9-10
 */
public class TaskExecutionWebServer {
    private static final int NUM = 100;
    //创建可以容纳100个请求的线程池
    private static final Executor exec = Executors.newFixedThreadPool(NUM);

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(8097);
        while (true) {
            final Socket connection = socket.accept();
            //如果accept检测到存在请求，则新创建任务
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    System.out.println("开启一个新的线程"+Thread.currentThread().getId()+"处理请求");
                    //curl loclhost:8098会输出: Socket[addr=/0:0:0:0:0:0:0:1,port=34743,localport=8097]
                    System.out.println("处理请求:"+connection.toString());
                }
            };
            //执行任务
            exec.execute(task);
        }
    }
}
