package com.mybatis.bean;

import java.util.List;
/**
 * 指令对应内容表
 * @author lxf
 *
 */
public class CommandContent {
    /**
     * 主键
     */
    private int id;
    /**
     * 自动回复内容
     */
    private String content;
    /**
     * 指令表command抓紧
     */
    private int command_id;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCommand_id() {
        return command_id;
    }

    public void setCommand_id(int command_id) {
        this.command_id = command_id;
    }

    @Override
    public String toString() {
        return "CommandContent [id=" + id + ", content=" + content + ", command_id=" + command_id + "]";
    }  
    
    
    
}
