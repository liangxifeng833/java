package com.concurrency.book.sixCharter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 串行的web服务器, 每次只有能处理一个任务
 * 即单线程处理,当A请求有很大的耗时工作,那么B请求等待
 * Create by liangxifeng on 19-9-10
 */
public class SingleThreadWebServer {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(8099);
        while(true) {
            //通过accept判断有无外部请求
            Socket connection = socket.accept();
            //curl loclhost:8098会输出: Socket[addr=/0:0:0:0:0:0:0:1,port=34743,localport=8099]
            System.out.println("处理请求:"+connection.toString());
        }
    }
}
