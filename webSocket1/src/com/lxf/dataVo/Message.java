package com.lxf.dataVo;
/**
 * 用来接收用户页面发来的数据的结构
 */
import java.util.Date;
import java.util.List;

public class Message {

    private  String  alert;   //
    //用户名
    private  List<String>  names;
    //发送的信息
    private  String  sendMsg;
    //信息来源
    private String  from;
    //时间
    private String  date;
    
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSendMsg() {
        return sendMsg;
    }

    public void setSendMsg(String sendMsg) {
        this.sendMsg = sendMsg;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public Message() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    
    
    
}
