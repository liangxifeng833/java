package jdbc;
/*
 * jdbc-数据库连接类
 */

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbcConnect {
	private static final String URL = "jdbc:mysql://192.168.9.22:3306/test";
	private static final String NAME = "common";
	private static final String PASS = "common";
	
	//用来存储数据库连接
	private static Connection con = null;
	//用来存储预编译
	public static PreparedStatement ps;
	//mysql结果集
	public static ResultSet rs;
	//Statement声明,用来执行sql
	public static Statement st;
	
	/*
	 * 静态初始化代码库在类第一次被加载的时候执行，创建数据库连接
	 */
	static
	{
		try {
			//加载mysql驱动
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL,NAME,PASS);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * 获取数据库连接
	 */
	public static Connection getConnection(){
		return con;
	}
}
