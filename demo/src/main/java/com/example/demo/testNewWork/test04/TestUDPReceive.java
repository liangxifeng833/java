package com.example.demo.testNewWork.test04;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * UDP 接收方demo
 * @author liangxifeng
 * @date 2022/11/7 11:52
 */

public class TestUDPReceive {
    public static void main(String[] args)  {
        System.out.println("老师上线了");
        //1. 创建套接字， 指定接收方的端口号
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket(9999);
            while (true) {
                //2. 有一个空的数据包，打算用来接收对方传过来的数据包
                byte[] bytes = new byte[1024];
                DatagramPacket dp = new DatagramPacket(bytes,bytes.length);
                //3. 接收对方的数据包(阻塞等待对方数据包)，然后放入我们的dp数据包填充
                ds.receive(dp); //接收完之后，dp就有内容了
                //4. 取出数据
                byte[] data = dp.getData();
                String s = new String(data,0,dp.getLength()); //dp.getLength()获取数据包中的有效长度
                System.out.println("学生对我说："+s);
                if( s.equals("byebye")) {
                    System.out.println("学生已经下线了，学生也下线了...");
                    break;
                }

                //老师进行回复，发送数据给学生
                //2. 准备要发送的数据
                Scanner scanner = new Scanner(System.in);
                System.out.print("老师：");
                //2. 准备要发送的数据
                String str = scanner.next(); //接收键盘输入的数据
                byte[] bytes2 = str.getBytes();
                /**
                 * 需要四个参数
                 * 1： 指定传输数据为字节数组
                 * 2：字节数组的长度
                 * 3. 封装接收方IP地址
                 * 4. 指定接收方端口号
                 */
                DatagramPacket dp2 = new DatagramPacket(bytes2,bytes2.length, InetAddress.getByName("localhost"),8888);
                //发送数据
                ds.send(dp2);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //5. 关闭资源
            ds.close();
        }
    }
}
