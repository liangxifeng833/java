package com.lxf.socket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.lxf.dataVo.Message;


/**
 * websocket 实现聊天室功能
 * @author lxf
 *
 */
@ServerEndpoint("/chatSocket")
public class ChatSocket {
    //存储每个管道的ChatSocket对象的set集合
    private  static  Set<ChatSocket>  sockets=new HashSet<ChatSocket>();
    /**存储以<用户名，ChatSocket对象>的map,注意：这里必须使用static修饰，因为
        在每个客户端连接过来的时候tomcat都会创建一个ChatSocket实例，当我们使用广播给多个用户发消息
        或者在TestServlet中自己实例化ChatSocket的时候，给指定用户发消息，我们会根据sMap(key)里面的session发送
        如果不指定为static，sMap会为每个对象局部私有，作用域只在本对象中
        使用static修饰，也就意味着所有对象共享sMap
     */
    private  static  Map<String, ChatSocket>  sMap=new HashMap<String, ChatSocket>(); 
    //用来存储所有登录人的用户名
    private  static  List<String>   names=new ArrayList<String>();
    //websocket会话
    private  Session  session;
    //当前用户名
    public String username;
    //Gson用来处理json和java对象转换的谷歌开源类库
    public Gson  gson=new Gson();
    
    /**
     * 当客户端建立连接的时候触发
     * @param session
     */
    @OnOpen
    public  void open(Session  session){
            this.session=session;
            sockets.add(this);
            System.out.println("连接成功：sessionId="+session.getId());
            String  queryString = session.getQueryString();
            System.out.println(queryString);
            this.username = queryString.substring(queryString.indexOf("=")+1);
            names.add(this.username);
            sMap.put(username, this);
     
            Message   message=new Message();
            message.setAlert(this.username+"进入聊天室！！当前聊天室人数"+sockets.size());
            message.setNames(names);
            
            broadcast(sockets, gson.toJson(message) );    
    }
    public Map<String, ChatSocket> getSMap()
    {
        return this.sMap;
    }
    
    /**
     * 行客户端发送消息　，当客户端触发ws.send()方法时触发
     * @param session
     * @param msg
     */
    @OnMessage
    public  void receive(Session  session,String msg ) throws IOException{
        //this.session.getBasicRemote().sendText(msg+"人数："+sockets.size());
        
        Message  message=new Message();
        message.setSendMsg(msg);
        message.setFrom(this.username);
        message.setDate(new Date().toLocaleString());
      //指定一个用户发消息
        sendSingleMsg(this.username, message); 
        //给所有用户广播发消息
        //broadcast(sockets, gson.toJson(message));
    }
    
    /**
     * 客户端关闭浏览器时触发
     * @param session
     */
    @OnClose
    public  void close(Session session){
        sockets.remove(this);
        names.remove(this.username);
        
        Message   message=new Message();
        message.setAlert(this.username+"退出聊天室！！");
        message.setNames(names);
        
        broadcast(sockets, gson.toJson(message));
    }
    
    /**
     * 给指定客户端发消息
     * @param ss
     * @param msg
     * @throws IOException 
     */
    public void sendSingleMsg(String uname, Message message) throws IOException
    {
        if(sMap.get(uname)!=null)
        {
            
            ChatSocket chatSocket = (ChatSocket)sMap.get(uname);
            System.out.println(chatSocket.session.getBasicRemote());
            //chatSocket.session.getBasicRemote().sendText("hello 111");
            chatSocket.session.getBasicRemote().sendText(gson.toJson(message));
        }else{
            System.out.println("获取对应用户信息丢失: " + username);
        }
        
    }
    
    /**
     * 给所有登录用户广播
     * @param ss
     * @param msg
     */
    public void broadcast(Set<ChatSocket>  ss ,String msg ){
        
        for (Iterator iterator = ss.iterator(); iterator.hasNext();) {
            ChatSocket chatSocket = (ChatSocket) iterator.next();
            try {
                System.out.println(chatSocket.session.getBasicRemote());
                chatSocket.session.getBasicRemote().sendText(msg);
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    
    

}
