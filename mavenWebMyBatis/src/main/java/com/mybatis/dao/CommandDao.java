package com.mybatis.dao;
/**
 *  与指令表对应的数据库操作类
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.mybatis.bean.Command;
import com.mybatis.bean.CommandContent;
import com.mybatis.bean.Message;
import com.mybatis.db.DBAccess;

public class CommandDao {
    /**
     * 根据条件查询多条指令列表
     * @param command
     * @param desc
     * @return
     */
    public List<Command> queryList(String command, String desc)
    {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            List<Command> commandList = new ArrayList<Command>();
            Command comObj = new Command();
            comObj.setName(command);
            comObj.setDesc(desc);
            commandList =  sqlSession.selectList("Command.queryCommandList",comObj);
            sqlSession.commit();
            for (Command com : commandList) {
                System.out.println("指令名＝"+com.getName()+", 描述：" + com.getDesc());
                System.out.println("=======对应内容列表是：");
                int i = 1;
                for(CommandContent content : com.getContentList()){
                    System.out.println("内容:" + i + content.getContent() );
                    i++;
                }
            }
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
  
    
    public static void main(String[] args){
        CommandDao md = new CommandDao();   
        md.queryList("六一", "");
        //测试删除
    }
}
