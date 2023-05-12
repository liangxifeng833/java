package com.example.demo.testNewWork.test01;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket server demo
 * @author liangxifeng
 * @date 2022/11/4 16:33
 */
public class TestServer {
    public static void main(String[] args) throws IOException {
        //1. 创建套接字：指定服务监听的端口号
        ServerSocket serverSocket = new ServerSocket(8888);
        //2. 等待客户端发来的信息, accept()方法的返回值是一个socket,
        //这个socket就是客户端的socket, 接到这个这个socket之后，客户端和服务器才真正产生了连接，才真正可以通信了
        Socket accept = serverSocket.accept();//阻塞方法：等待接收客户端的数据, 什么时候接收到了数据，什么时候继续执行
        //3. 用输入流接收用户的请求
        InputStream inputStream = accept.getInputStream();
        DataInputStream dis = new DataInputStream(inputStream);
        //4. 读取客户端发来的数据
        String str = dis.readUTF();
        System.out.println("客户端发来的数据为:"+str);

        //向客户端发送数据
        OutputStream outputStream = accept.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeUTF("你好，我是服务器端，我接收到了你的请求了");

        //5. 关闭流 + 关闭网络资源
        dataOutputStream.close();
        outputStream.close();
        dis.close();
        inputStream.close();
        accept.close();
        serverSocket.close();


    }
}
