package com.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.mybatis.bean.Message;

/**
 * 与Message.xml配置文件相对应的接口
 * @author lxf
 * @2017-06-08
 *
 */
public interface IMessage {
    /*
     * 查询多条message信息表数据方法，方法名queryList=Message.xml中对应的查询id名
     */
    public List<Message>  queryList(Message msg);
    
    /**
     * 查询数量
     */
    public int queryCount(Message msg);
    /*
     * 查询多条message信息表数据方法，带分页功能
     */
    public List<Message> queryListWithPage(Map<String,Object> map);
    
    /*
     * 使用拦截器实现分页
     */
    public List<Message> queryListWithPageByInterceptor(Message msg);
    /**
     * 批量新增
     */
    public void insertBatch(List<Message> msg);
}
