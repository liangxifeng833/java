package com.mybatis.bean;

public class Message {
    private int id;
    private String command;
    private String desc;
    private String content;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCommand() {
        return command;
    }
    public void setCommand(String command) {
        this.command = command;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    @Override
    public String toString() {
        return "Message [id=" + id + ", command=" + command + ", desc=" + desc + ", content=" + content + "]";
    }
    
    
}
