package com.concurrency.book.sixCharter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 在结构上类似于单线程版本,主线程不断交期执行"接收外部连接"
 * 但是区别在于对于每个连接,主循环都将创建一个新的线程处理,
 * 而不是在主循环中进行处理
 * Create by liangxifeng on 19-9-10
 */
public class ThreadPerTaskWebServer {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(8098);
        while(true) {
            //通过accept判断有无外部请求
            final Socket connection = socket.accept();
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    System.out.println("开启一个新的线程"+Thread.currentThread().getId()+"处理请求");
                    //curl loclhost:8098会输出: Socket[addr=/0:0:0:0:0:0:0:1,port=34743,localport=8098]
                    System.out.println("处理请求:"+connection.toString());
                }
            };
            new Thread(task).start();

        }
    }
}
