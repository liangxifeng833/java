package com.lxf.socket;
/**
 * 注解方式实现websocket,简单实现echo字符串功能
 * @author lxf
 *
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/echo")
public class EchoSocket {
    private List<Session> sessions = new ArrayList<Session>();
    
    /**
     * 当客户端建立连接的时候触发
     * @param session
     */
    @OnOpen
    public void open(Session session)
    {
        System.out.println("连接成功：sessionId="+session.getId());
        sessions.add(session);
    }
    
    /**
     * 行客户端发送消息　，当客户端触发ws.send()方法时触发
     * @param session
     * @param msg
     * @param last
     */
    @OnMessage
    public void echoTextMessage(Session session, String msg, boolean last) {
        try {
            if (session.isOpen()) {
                session.getBasicRemote().sendText("客户端浏览器输入的内容是："+msg+"session会话的个数是:"+sessions.size(), last);
            }
        } catch (IOException e) {
            try {
                session.close();
            } catch (IOException e1) {
                // Ignore
            }
        }
    }
    /**
     * 客户端关闭浏览器时触发
     * @param session
     */
    @OnClose
    public void  close(Session  session){
        System.out.println(session.getId()+"session 关闭啦");
    }
    
}
