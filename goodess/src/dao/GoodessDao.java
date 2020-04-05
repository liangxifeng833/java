package dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



import model.Goodess;
import db.DBUtil;
/**
 * 模型层
 */

public class GoodessDao {
	//定义mysql连接句柄
	private static Connection conn = null;
	private static Statement stmt = null;
	static
	{
		conn = DBUtil.getConnection();
		//创建Statement对象
		try {
			stmt = (Statement) conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	/*
	 * 新增女神
	 * param [Goodess] gs 女神对象
	 */
	public void addGoddess(Goodess gs) throws SQLException
	{
		String sql = " insert into goodess (user_name,sex,birthday,add_time,update_time) " + 
					"values (" + 
					"?,?,?,current_date,current_date)";
		//预编译
		PreparedStatement ptmt = conn.prepareStatement(sql);
		//为预编译的占位符赋值
		ptmt.setString(1, gs.getUser_name());
		ptmt.setInt(2,gs.getSex());
		ptmt.setDate(3, new Date(gs.getBirthday().getTime()));
		//执行sql语句
		ptmt.execute();
	}
	/*
	 * 修改女神
	 * param [Goodess] gs 女神对象
	 */
	public void updateGoddess(Goodess gs) throws SQLException
	{
		String sql = " UPDATE goodess SET user_name = ? , sex = ? , birthday = ? , update_time = current_date  " + 
				" WHERE id = " + " ? ";
		//预编译
		PreparedStatement ptmt = conn.prepareStatement(sql);
		//为预编译的占位符赋值
		ptmt.setString(1, gs.getUser_name());
		ptmt.setInt(2,gs.getSex());
		ptmt.setDate(3, new Date(gs.getBirthday().getTime()));
		ptmt.setInt(4, gs.getId());
		//执行sql语句
		ptmt.execute();
		
	}
	/*
	 * 删除女神
	 */
	public void delGoddess(Integer id) throws SQLException
	{
		String sql = " DELETE  FROM goodess WHERE  id = ? LIMIT 1";
		//预编译
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, id);
		ptmt.execute();
	}
	/*
	 * 查询女神
	 * param [list] param 查询条件list
	 */
	public List<Goodess> query(List<Map<String, Object>> param) throws SQLException
	{
		StringBuilder sql =  new StringBuilder("SELECT * FROM goodess WHERE 1=1 ");
		if(param !=null && param.size()>0)
		{
			for(int i=0; i<param.size(); i++)
			{
				Map<String,Object> paramNew = param.get(i);
				sql.append("AND " + paramNew.get("name"));
				sql.append(" " + paramNew.get("rela"));
				sql.append(" " + paramNew.get("value"));
			}		
		}

		//print sql
		System.out.println(sql.toString());
		//预编译
		PreparedStatement ptmt = conn.prepareStatement(sql.toString());
		//ResultSet res = stmt.executeQuery("select * from goodess");
		//建立返回女神返回值集合，作为返回值
		List<Goodess> gd = new ArrayList<Goodess>();
		//建立用来存储女神对象的变量名，初值为null
		Goodess g = null;
		ResultSet res = ptmt.executeQuery();
		while(res.next())	
		{
			g = new Goodess();
			g.setId(res.getInt("id"));
			//g.setAdd_time(res.getInt("add_time"));
			g.setUser_name(res.getString("user_name"));
			g.setSex(res.getInt("sex"));
			g.setBirthday(res.getDate("birthday"));	
			g.setAdd_time(res.getDate("add_time"));
			gd.add(g);
		}	
		return gd;
	}
	/*
	 * 查询单个女神
	 */
	public Goodess get(Integer id) throws SQLException
	{
		Goodess g = new Goodess();
		String sql = "SELECT * FROM goodess WHERE id = ?";
		//预编译
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, id);
		//获取结果集合
		ResultSet res = ptmt.executeQuery();
		res.next();
		g.setId(res.getInt("id"));
		g.setSex(res.getInt("sex"));
		g.setUser_name(res.getString("user_name"));
		g.setAdd_time(res.getDate("add_time"));
		g.setUpdate_time(res.getDate("update_time"));
		g.setBirthday(res.getDate("birthday"));	
		return g;
	}
}
