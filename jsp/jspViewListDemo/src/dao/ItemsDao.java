package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entity.Items;
import util.DBHelper;


public class ItemsDao {
	
	//mysql连接句柄
	private static Connection conn = null;
	//预处理 句柄
	private static PreparedStatement ptmt = null;
	static
	{
		try {
			conn = DBHelper.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//获取所有商品信息
	public ArrayList<Items> getAll()
	{
		ArrayList<Items> list = new ArrayList<Items>();
		String sql = "SELECT * from items";
		ResultSet res = null;
		try {
			ptmt = conn.prepareStatement(sql);
			//获取查询结果
			res = ptmt.executeQuery();
			if(res!=null)
			{
				while(res.next())
				{
					Items item  = new Items();
					item.setId(res.getInt("id"));
					item.setName(res.getString("name"));
					item.setCity(res.getString("city"));
					item.setNumber(res.getInt("number"));
					item.setPrice(res.getInt("price"));
					item.setPicture(res.getString("picture"));
					list.add(item);
				}
				return list;
			}else
			{
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally {
			//释放数据集对象
			if(res!=null)
			{
				try {
					res.close();
					res = null;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(ptmt!=null)
			{
				try {
					ptmt.close();
					ptmt = null;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	//获取商品详情信息
	public Items detail(int id)
	{			
		Items item  = new Items();
		ResultSet res = null;
		try {	
			String sql = "SELECT * FROM items WHERE id = ?";
			//System.out.println(sql);
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, id);
			//获取查询结果
			res = ptmt.executeQuery();
			if(res.next())
			{
				item.setId(res.getInt("id"));
				item.setName(res.getString("name"));
				item.setCity(res.getString("city"));
				item.setNumber(res.getInt("number"));
				item.setPrice(res.getInt("price"));
				item.setPicture(res.getString("picture"));
				return item;
			}else
			{
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			//释放数据集对象
			if(res!=null)
			{
				try {
					res.close();
					res = null;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(ptmt!=null)
			{
				try {
					ptmt.close();
					ptmt = null;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	//查看浏览过的商品信息
	public ArrayList<Items> getViewedList(String list)
	{
		ArrayList<Items> itemList = new ArrayList<Items>();
		if(list!=null && list.length()>0)
		{
			String[] arr = list.split(",");
			if(arr!=null && arr.length>0)
			{
				//始终显示最新５条记录
				if(arr.length>=5)
				{
					for(int i=arr.length-1; i>=arr.length-5; i--)
					{
						Items item =this.detail(Integer.parseInt(arr[i]));
						itemList.add(item);
					}
				}else
				{
					for(int i=arr.length-1; i>=0; i--)
					{
						Items item =this.detail(Integer.parseInt(arr[i]));
						itemList.add(item);
					}
				}
			}
			return itemList;
		}else
		{
			return null;
		}
 	}
}
