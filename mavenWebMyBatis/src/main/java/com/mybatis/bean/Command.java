package com.mybatis.bean;

import java.util.List;

/**
 * 指令表
 * @author lxf
 */
public class Command {
    /*
     * 主键
     */
    private int id;
    /*
     * 指令名称
     */
    private String name;
    /*
     * 指令描述信息
     */
    private String desc;
    /**
     *  一条指令对应的自动回复内容列表
     */
    private List<CommandContent> contentList;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public List<CommandContent> getContentList() {
        return contentList;
    }
    public void setContentList(List<CommandContent> contentList) {
        this.contentList = contentList;
    }
    @Override
    public String toString() {
        return "Command [id=" + id + ", name=" + name + ", desc=" + desc + ", contentList=" + contentList + "]";
    }
}
