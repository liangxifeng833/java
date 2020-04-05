package com.corefire.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import com.corefire.model.AlipayAnnal;
import com.corefire.util.JdbcConnect;

/**
 * 支付宝收款表操作类
 * @author liangxifeng
 * @date 2017-04-15
 *
 */

public class AlipayAnnalDao {
    //定义mysql连接句柄
    private static Connection conn = null;
    private static Statement stmt = null;
    
    {
        try {
            conn = JdbcConnect.getConnect();
            //创建Statement对象
            stmt = (Statement) conn.createStatement();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /*
     * 新增收款数据
     * @param AlipayAnnal pay 支付宝收款实体对象
     * @return int 影响行数, 新增成功返回1
     */
    public int add(AlipayAnnal pay) throws SQLException
    {
        String sql = " insert into alipay_annal (appid,value,charge,add_time,modify_time) " + 
                    "values (" + 
                    "?,?,?,?,?)";
        //预编译
        PreparedStatement ptmt = conn.prepareStatement(sql);
        //为预编译的占位符赋值
        ptmt.setString(1,pay.getAppid());
        ptmt.setBigDecimal(2, pay.getValue());
        ptmt.setBigDecimal(3, pay.getCharge());
        ptmt.setDate(4, new Date(pay.getAdd_time().getTime()));
        ptmt.setDate(5, new Date(pay.getModify_time().getTime()));
        //执行sql语句
        return ptmt.executeUpdate();
    }
}
