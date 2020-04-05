package util;
/*
 * 数据库类
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
	//数据库驱动
	private static final String driver = "com.mysql.jdbc.Driver";
	//数据库连接地址　
	private static final String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8";
	private static final String username = "common";
	private static final String password = "common";
	private static Connection  con = null;
	
	//静态代码库加载驱动
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//单例模式，返回数据库连接对象
	public static Connection getConnection() throws SQLException{
		if(null == con)
		{
			con = DriverManager.getConnection(url, username, password);
			return con;
		}
		return con;
	}
	
	public static void main(String[] args) {
		try {
			Connection conn = DBHelper.getConnection();
			if(conn !=null )
			{
				System.out.println("数据库连接正常!");
			}else
			{
				System.out.println("数据库连接异常!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
