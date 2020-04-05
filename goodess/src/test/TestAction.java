package test;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import action.GoodessAction;
import model.Goodess;
/**
 * 测试action类
 * @author lxf
 *
 */

public class TestAction {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		
		GoodessAction action = new GoodessAction();	
		Goodess gs1 = new Goodess();
		//测试新增女神
		/*
		gs1.setUser_name("liangxifeng modify new");
		gs1.setSex(1);
		gs1.setBirthday(new Date());
		action.add(gs1);
		*/
		//测试修改女神
		gs1.setUser_name("lisi-update");
		gs1.setSex(4);
		gs1.setBirthday(new Date());
		gs1.setId(2);
		action.edit(gs1);
		
		//测试删除女神
		action.del(4);
		
		//测试查询女神
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
		List<Goodess> gs = action.query(params);
		//遍历查询的结果
		
		for (Goodess goodess : gs) {
			System.out.println(goodess.toString());		
		}
		
	}

}
