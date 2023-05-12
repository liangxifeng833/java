package com.example.demo.testNewWork.test03;

import com.example.demo.testNewWork.test02.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 专门负责处理用户请求的，接收或发送数据的线程处理类
 * @author liangxifeng
 * @date 2022/11/7 11:19
 */

public class ServerThread extends Thread{
    DataOutputStream dataOutputStream = null;
    OutputStream outputStream = null;
    ObjectInputStream ois = null;
    InputStream inputStream = null;
    Socket socket = null;

    ServerThread( Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //3. 用输入流接收用户的请求
            inputStream = socket.getInputStream();
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
            outputStream = socket.getOutputStream();
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
                if (socket != null ) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
