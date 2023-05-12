package com.example.demo.testNewWork.test03;

import com.example.demo.testNewWork.test02.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket server 多线程处理用户请求
 * @author liangxifeng
 * @date 2022/11/4 16:33
 */
public class TestServer {
    public static void main(String[] args)  {
        System.out.println("服务器启动了...");
        //1. 创建套接字：指定服务监听的端口号
        ServerSocket serverSocket = null;
        Socket accept = null;
        try {
            serverSocket = new ServerSocket(8888);
            //2. 等待客户端发来的信息, accept()方法的返回值是一个socket,
            //这个socket就是客户端的socket, 接到这个这个socket之后，客户端和服务器才真正产生了连接，才真正可以通信了
            //加入死循环，服务器一直监听客户端是否发送数据
            int count = 0;
            while (true) {
                accept = serverSocket.accept();//阻塞方法：等待接收客户端的数据, 什么时候接收到了数据，什么时候继续执行
                //每次传递过来的客户端请求，靠一个线程处理
                new ServerThread(accept).start();
                count++;
                System.out.println("当前是第"+count+"个用户访问我们的服务器,对应的用户是："+accept.getInetAddress());
            }

        } catch (IOException  e) {
            e.printStackTrace();
        }
    }
}
