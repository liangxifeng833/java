package com.example.demo.testNewWork.test02;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket server demo
 * @author liangxifeng
 * @date 2022/11/4 16:33
 */
public class TestServer {
    public static void main(String[] args)  {
        System.out.println("服务器启动了...");
        //1. 创建套接字：指定服务监听的端口号
        ServerSocket serverSocket = null;
        Socket accept = null;
        DataOutputStream dataOutputStream = null;
        OutputStream outputStream = null;
        ObjectInputStream ois = null;
        InputStream inputStream = null;
        try {
            serverSocket = new ServerSocket(8888);
            //2. 等待客户端发来的信息, accept()方法的返回值是一个socket,
            //这个socket就是客户端的socket, 接到这个这个socket之后，客户端和服务器才真正产生了连接，才真正可以通信了
            accept = serverSocket.accept();//阻塞方法：等待接收客户端的数据, 什么时候接收到了数据，什么时候继续执行
            //3. 用输入流接收用户的请求
            inputStream = accept.getInputStream();
            ois = new ObjectInputStream(inputStream);
            //4. 读取客户端发来的数据
            User user = (User) (ois.readObject());
            //对客户端发送过来的对象进行验证
            boolean flag = false;
            if( user.getName().equals("娜娜") && user.getPwd().equals("123123")) {
                flag = true;
            }
            System.out.println("客户端发来的数据为:"+user);

            //向客户端发送数据
            outputStream = accept.getOutputStream();
            dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeBoolean(flag);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            //5. 关闭流 + 关闭网络资源
            try {
                if (dataOutputStream != null ) {
                    dataOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (outputStream != null ) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (ois != null ) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (inputStream != null ) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (accept != null ) {
                    accept.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (serverSocket != null ) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
