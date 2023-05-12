package com.example.demo.testNewWork.test04;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * UDP发送方式 demo
 * @author liangxifeng
 * @date 2022/11/7 11:47
 */

public class TestUDPSend {
    public static void main(String[] args) {
        System.out.println("学生上线了...");
        //1. 准备套接字：指定发送方端口
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket(8888);
            while (true) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("学生：");
                //2. 准备要发送的数据
                String str = scanner.next(); //接收键盘输入的数据
                byte[] bytes = str.getBytes();
                /**
                 * 需要四个参数
                 * 1： 指定传输数据为字节数组
                 * 2：字节数组的长度
                 * 3. 封装接收方IP地址
                 * 4. 指定接收方端口号
                 */
                DatagramPacket dp = new DatagramPacket(bytes,bytes.length, InetAddress.getByName("localhost"),9999);
                //发送数据
                ds.send(dp);
                if( str.equals("byebye")) {
                    System.out.println("学生已经下线了..");
                    break;
                }

                //接收老师发送回来的数据
                //2. 有一个空的数据包，打算用来接收对方传过来的数据包
                byte[] bytes2 = new byte[1024];
                DatagramPacket dp2 = new DatagramPacket(bytes2,bytes2.length);
                //3. 接收对方的数据包，然后放入我们的dp数据包填充
                ds.receive(dp2); //接收完之后，dp就有内容了
                //4. 取出数据
                byte[] data = dp2.getData();
                String s = new String(data,0,dp2.getLength()); //dp.getLength()获取数据包中的有效长度
                System.out.println("老师对学生说："+s);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            ds.close();
        }
    }
}
