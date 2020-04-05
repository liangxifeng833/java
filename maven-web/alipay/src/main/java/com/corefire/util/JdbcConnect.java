package com.corefire.util;
/*
 * jdbc-数据库连接类
 * @author: liangxifeng
 * @date: 2017-04-15
 */

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.fourspaces.couchdb.Session;

public class JdbcConnect {
    private static String url = null;
    private static String user = null;
    private static String pass = null;
	//用来存储数据库连接
	private static Connection con = null;
	//用来存储预编译
	public static PreparedStatement ps;
	//mysql结果集
	public static ResultSet rs;
	//Statement声明,用来执行sql
	public static Statement st;
	
	/*
	 * 获取数据库连接
	 */
	public static Connection getConnect() throws ClassNotFoundException{
        Properties props = new Properties();
        //读取配置文件
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
        try {
            props.load(is);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        url = props.getProperty("mysql_url");
        user = props.getProperty("mysql_db_user");
        pass = props.getProperty("mysql_db_pass");
        //加载mysql驱动
        Class.forName("com.mysql.jdbc.Driver");
        try {
            con = DriverManager.getConnection(url,user,pass);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return con;
	}
}
