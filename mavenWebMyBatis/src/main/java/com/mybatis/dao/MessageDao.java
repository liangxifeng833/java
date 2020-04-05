package com.mybatis.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.lxf.entity.Page;
import com.mybatis.bean.Message;
import com.mybatis.db.DBAccess;

public class MessageDao {
    /**
     * 根据条件查询多条
     * @param command
     * @param desc
     * @return
     */
    public List<Message> queryList(String command, String desc)
    {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            List<Message> msg = new ArrayList<Message>();
            Message msgObj = new Message();
            msgObj.setCommand(command);
            msgObj.setDesc(desc);
            IMessage imsg = sqlSession.getMapper(IMessage.class);    
            msg = imsg.queryList(msgObj);
            //msg =  sqlSession.selectList("Message.queryList",msgObj);
            sqlSession.commit();
            for (Message message : msg) {
                System.out.println(message.getCommand());
            }
            return msg;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if(sqlSession != null)
            {
                sqlSession.close();
            }
        }
        return null;
    }
    /**
     * 根据条件查询总数
     * @param command
     * @param desc
     * @return int
     */
    public int queryCount(String command, String desc)
    {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            List<Message> msg = new ArrayList<Message>();
            Message msgObj = new Message();
            msgObj.setCommand(command);
            msgObj.setDesc(desc);
            IMessage imsg = sqlSession.getMapper(IMessage.class);    
            int msgCount = imsg.queryCount(msgObj);
            sqlSession.commit();
            return msgCount;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if(sqlSession != null)
            {
                sqlSession.close();
            }
        }
        return 0;
    }
    /**
     * 根据条件查询多条,带分页功能
     * @param command
     * @param desc
     * @param curPage 当前页码
     * @return
     */
    public List<Message> queryListWithPage(String command, String desc,int curPage)
    {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            List<Message> msg = new ArrayList<Message>();
            Message msgObj = new Message();
            msgObj.setCommand(command);
            msgObj.setDesc(desc);
            //获取接口式编程中，message.xml中的select.id对应的接口
            IMessage imsg = sqlSession.getMapper(IMessage.class);    
            
            
            //获取记录总数
            int count = imsg.queryCount(msgObj);
            //实例化分页类
            Page page = new Page();
            //设置当前页码
            page.setCurrentPage(curPage);
            //设置记录总数
            page.setTotalNumber(count);
            //设置每页显示2条记录
            page.setPageNumber(2);
            
            Map<String,Object> mapParam = new HashMap<String, Object>();
            mapParam.put("message", msgObj);
            mapParam.put("page", page);
            
            msg = imsg.queryListWithPage(mapParam);
            sqlSession.commit();
            for (Message message : msg) {
                System.out.println(message.getCommand());
            }
            return msg;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if(sqlSession != null)
            {
                sqlSession.close();
            }
        }
        return null;
    }
    /**
     * 根据条件查询多条,使用拦截器实现分页
     * @param command
     * @param desc
     * @param curPage 当前页码
     * @return
     */
    public List<Message> queryListWithPageByInterceptor(String command, String desc)
    {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            List<Message> msg = new ArrayList<Message>();
            Message msgObj = new Message();
            msgObj.setCommand(command);
            msgObj.setDesc(desc);
            IMessage imsg = sqlSession.getMapper(IMessage.class);    
            msg = imsg.queryListWithPageByInterceptor(msgObj);
            //msg =  sqlSession.selectList("Message.queryList",msgObj);
            sqlSession.commit();
            for (Message message : msg) {
                System.out.println(message.getCommand());
            }
            return msg;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if(sqlSession != null)
            {
                sqlSession.close();
            }
        }
        return null;
    }
    /**
     * 根据主键删除
     */
    public void delOne(int  id)
    {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            sqlSession.selectList("Message.deleteOne",id);
            sqlSession.commit();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if(sqlSession != null)
            {
                sqlSession.close();
            }
        }
    }
    /**
     * 批量删除
     * @param args
     */
    public void delBatch(List<Integer> ids)
    {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            sqlSession.selectList("Message.deleteBatch",ids);
            sqlSession.commit();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if(sqlSession != null)
            {
                sqlSession.close();
            }
        } 
    }
    /**
     * 修改message信息
     * @param args
     */
    public void updateById(Message msg)
    {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            sqlSession.selectList("Message.updateOne",msg);
            sqlSession.commit();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if(sqlSession != null)
            {
                sqlSession.close();
            }
        } 
    }
    /**
     * 新增数据
     * @param args
     */
    public void add(Message msg)
    {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            sqlSession.selectList("Message.insertOne",msg);
            sqlSession.commit();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if(sqlSession != null)
            {
                sqlSession.close();
            }
        } 
        
    }
    /**
     * 批量新增数据
     * @param args
     */
    public void addBatch(List<Message> msgList)
    {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            //获取Message.xml对应接口
            IMessage imsg = sqlSession.getMapper(IMessage.class);    
            imsg.insertBatch(msgList);
            sqlSession.commit();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if(sqlSession != null)
            {
                sqlSession.close();
            }
        } 
        
    }
    
    public static void main(String[] args){
        MessageDao md = new MessageDao();   
        md.queryList("查看", "");
        //测试删除
    }
}
