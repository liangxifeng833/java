package com.example.demo.testNewWork.test03;

import com.example.demo.testNewWork.test02.User;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * socket client demo 向服务器端发送对象
 * @author liangxifeng
 * @date 2022/11/4 16:27
 */

public class TestClient {
    public static void main(String[] args) {
        System.out.println("客户端启动了...");
        //1.创建套接字：指定服务器的ip和端口号
        Socket socket = null;
        DataInputStream dis = null;
        OutputStream os = null;
        ObjectOutputStream oos = null;
        InputStream inputStream = null;
        try {
            socket = new Socket("127.0.0.1",8888);
            //录入用户的账号和密码信息
            Scanner sc = new Scanner(System.in);
            System.out.println("请录入您的账号: ");
            String name = sc.next(); //接收账号
            System.out.println("请录入您的密码: ");
            String pwd = sc.next(); //接收密码
            //将账号和密码封装为一个对象
            User user = new User(name,pwd);


            //2.向外发送数据，利用输出流
            os = socket.getOutputStream();
            oos = new ObjectOutputStream(os);
            //利用OutputStream输出流就可以向外发送数据了,但是没有直接发送对象的方法，
            // 所以有又在OutputStream外面套了一个处理流：ObjectOutputStream
            oos.writeObject(user);

            //接收服务器端发送的请求
            inputStream = socket.getInputStream();
            dis = new DataInputStream(inputStream);
            boolean b = dis.readBoolean();
            if( b ) {
                System.out.println("恭喜您，登录成功！");
            }else {
                System.out.println("对不起，登录失败！");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //3.关闭输出流 + 关闭网络资源
            try {
                if(dis != null) {
                    dis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(inputStream != null) {
                    inputStream.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if( os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
