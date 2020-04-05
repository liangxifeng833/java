package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Profit;
import beans.Trading;
import jdbc.jdbcConnect;
import sun.java2d.cmm.ProfileActivator;

/*
 * 逻辑层处理
 * @author: liangxifeng
 * @date:2017-02-16
 */

public class Service {
	//用来存储数据库连接
	private Connection dbconn;
	//用来存储预编译
	private  PreparedStatement ps;
	//mysql结果集
	private  ResultSet rs;
	//Statement声明,用来执行sql
	private  Statement st;
	//sql语句
	private String sql;
	private List list;
	
	public List getProfit()
	{
		list = new ArrayList<Trading>();
		//获取数据库连接
		dbconn = jdbcConnect.getConnection();
		System.out.println(dbconn.toString());
		try {
			Statement st  = (Statement)dbconn.createStatement();
			StringBuilder sql = new StringBuilder(" SELECT  g.goods_id,g.goods_name,g.cost_price,g.selling_price,t.add_time, count(*) AS times, sum(t.number) AS numbers  FROM trading t,goods g WHERE t.goods_id = g.goods_id GROUP BY g.goods_id");
			ResultSet res =  st.executeQuery(sql.toString());
			
			while( res.next() )
			{
				Profit pf = new Profit();
				pf.setGoodsId(res.getInt("goods_id"));
				pf.setGoodsName(res.getString("goods_name"));
				pf.setNumber(res.getInt("numbers")); //卖出数量
				pf.setSellingPrice(res.getFloat("selling_price")); //售价
				pf.setCostPrice(res.getFloat("cost_price"));//成本价
				pf.setTimes(res.getInt("times")); //交易笔数
				
				//盈利 = ( 售价 - 成本 ) * 卖出数量
				float profit = ( res.getFloat("selling_price")-res.getFloat("cost_price") ) * res.getInt("numbers");
				pf.setProfit(profit);
				
				list.add(pf);
				System.out.println("商品名称 : " + res.getString("goods_name") + ", 成本价 : " + res.getFloat("cost_price") + ", 卖出数量 : " + res.getInt("numbers") + ", 交易笔数 :  " +res.getInt("times") + ",盈利: " +profit  );
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
