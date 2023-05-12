package com.example.demo.testNewWork.test01;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * socket client demo 向服务器端发送简单字符串
 * @author liangxifeng
 * @date 2022/11/4 16:27
 */

public class TestClient {
    public static void main(String[] args) throws IOException {
        //1.创建套接字：指定服务器的ip和端口号
        Socket socket = new Socket("127.0.0.1",8888);

        //2.向外发送数据，利用输出流
        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        //利用OutputStream输出流就可以向外发送数据了,但是没有直接发送字符串的方法，
        // 所以有又在OutputStream外面套了一个处理流：DataOutputStream
        dos.writeUTF("你好....");

        //接收服务器端发送的请求
        InputStream inputStream = socket.getInputStream();
        DataInputStream dis = new DataInputStream(inputStream);
        String s = dis.readUTF();
        System.out.println("服务器端返回的数据："+s);


        //3.关闭输出流 + 关闭网络资源
        dis.close();
        inputStream.close();
        dos.close();
        os.close();
        socket.close();
    }
}
