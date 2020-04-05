package action;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.GoodessDao;
import model.Goodess;

public class GoodessAction {
	
	//新增女神方法
	public void add(Goodess goodess) throws Exception
	{
		GoodessDao dao = new GoodessDao();
		dao.addGoddess(goodess);
	}
	//修改女神方法
	public void edit(Goodess goodess) throws Exception
	{
		GoodessDao dao = new GoodessDao();
		dao.updateGoddess(goodess);
	}
	//删除女神方法
	public void del(Integer id) throws Exception
	{
		GoodessDao dao = new GoodessDao();
		dao.delGoddess(id);
	}
	//根据条件查询女神
	public List<Goodess> query(List<Map<String,Object>> params) throws Exception
	{
		GoodessDao dao = new GoodessDao();
		return dao.query(params);
	}
	//查询单个女神
	public Goodess get(Integer id) throws SQLException
	{
		GoodessDao dao = new GoodessDao();
		return dao.get(id);
	}
	

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		GoodessDao g = new GoodessDao();
		//查询女神多条记录
		//定义查询参数

		List<Map<String,Object>> params = new ArrayList<Map<String,Object>>();
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("name", "user_name");
		param.put("rela", "like");
		param.put("value", "'%liangxifeng%'");
		params.add(param);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", "sex");
		map.put("rela", "=");
		map.put("value", "1");
		params.add(map);
		List<Goodess> gs = g.query(params);
		//遍历查询的结果
		
		for (Goodess goodess : gs) {
			System.out.println(goodess.toString());		
		}
		
			
		
		Goodess gs1 = new Goodess();
		gs1.setUser_name("liangxifeng modify new");
		gs1.setSex(1);
		gs1.setBirthday(new Date());
		gs1.setUpdate_time(new Date());
		//gs1.setId(1);
		//新增女神
		g.addGoddess(gs1);
		/*
		//修改女神
		g.updateGoddess(gs1);
		//删除女神
		//g.delGoddess(3);
		
		//查询单个女神
		Goodess gObj = g.get(1);
		System.out.println(gObj.toString());
		*/
	}

}
