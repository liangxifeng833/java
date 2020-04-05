package com.mybatis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import com.mybatis.bean.Command;
import com.mybatis.bean.Message;
import com.mybatis.dao.CommandDao;
import com.mybatis.dao.MessageDao;
import com.mybatis.db.DBAccess;

public class TestMybatis {
    @Test
    public void testGetDBsqlSession()
    {
        SqlSession  sqlSession = null;
        System.out.println("test mybatis GetDBsqlSessoin =======================");
        try {
             sqlSession = new DBAccess().getSqlSession();
            Assert.assertNotNull(sqlSession);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            if(sqlSession != null)
            {
                sqlSession.close();
            }
        }
    }
    /**
     * 单元测试新增操作
     */
    @Test
    public void testAdd()
    {
        MessageDao md = new MessageDao();
        Message msg = new Message();
        msg.setCommand("新增指令");
        msg.setDesc("新增指令描述");
        msg.setContent("新增指令内容");
        md.add(msg);
    }
    /**
     * 单元测试批量新增
     */
    @Test
    public void testAddBatch()
    {
        MessageDao md = new MessageDao();
        Message msg = new Message();
        List<Message> msgList = new ArrayList<Message>();
        msg.setCommand("批量测试新增指令1");
        msg.setContent("批量测试新增内容１");
        msg.setDesc("批量测试新增描述1");
        msgList.add(msg);
        Message msg2 = new Message();
        msg2.setCommand("批量测试新增指令2");
        msg2.setContent("批量测试新增内容2");
        msg2.setDesc("批量测试新增描述2");
        msgList.add(msg2);
        md.addBatch(msgList);
    }
    /**
     * 单元测试修改message表数据
     */
    @Test
    public void testUpdateOne()
    {
        MessageDao md = new MessageDao();   
        List<Message> msgList = new ArrayList<Message>();
        msgList = md.queryList("查看", "");
        msgList.get(0).setContent("测试修改查看指令的第一条数据内容");
        md.updateById(msgList.get(0));
    }
    /**
     * 单元测试查询多条记录
     */
    @Test
    public void testSelectList()
    {
        MessageDao md = new MessageDao();   
        md.queryList("查看", "");
    }
    /**
     * 单元测试查询总记录数
     */
    @Test
    public void testQueryCount()
    {
        MessageDao md = new MessageDao();
        int count = md.queryCount("查看", "");
        System.out.println(count);
    }
    /**
     * 单元测试查询总记录数,带分页功能
     */
    @Test
    public void testQueryListWithPage()
    {
        MessageDao md = new MessageDao();
        md.queryListWithPage("查看", "", 2);
    }
    /**
     * 单元测试查询多条记录，使用拦截器实现分页功能
     */
    @Test
    public void testQueryListWithPageByInterceptor()
    {
        MessageDao md = new MessageDao();
        md.queryListWithPageByInterceptor("查看", "");
    }
    /**
     * 单元测试根据主键删除单条记录
     */
    @Test
    public void testDelOne()
    {
        MessageDao md = new MessageDao();   
        md.delOne(6);
    }
    /**
     * 单元测试根据主键批量删除
     */
    @Test
    public void testDelBatch()
    {
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(Integer.valueOf("5"));
        ids.add(Integer.valueOf("4"));
        MessageDao md = new MessageDao();   
        md.delBatch(ids);
    }
    /**
     * 单元测试输入一对多关联关系，指令表和内容表（列表）
     */
    @Test
    public void testGetCommandToContent()
    {
        Command command = new Command();
        CommandDao cDao = new CommandDao();
        List<Command> commandList =  new ArrayList<Command>();
        commandList = cDao.queryList("六一", "");
    }
}
