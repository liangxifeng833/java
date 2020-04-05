package view;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import action.GoodessAction;

import model.Goodess;

/**
 * view层测试
 */

public class View {
	
	private static final String CONTXT = "欢迎来到女神禁区: \n" + 
			"下面是女神禁区的功能列表：\n" + 
			"[MAIN/M]:主菜单" + 
			"[QUERY/Q]:查看女神信息\n" +
			"[GET/G]:查看单个女神信息\n" + 
			"[ADD/A]:添加女神信息\n" +
			"[UPDATE/U]:更新女神信息\n" + 
			"[DELETE/D]: 删除女神信息\n" + 
			"[EXIT/E]: 退出女神禁区\n" + 
			"[BREAK/B]: 退出当前功能，返回主菜单";
	private static final String OPERATION_MAIN = "MAIN";
	private static final String OPERATION_QUERY = "QUERY";
	private static final String OPERATION_GET = "GET";
	private static final String OPERATION_ADD = "ADD";
	private static final String OPERATION_UPDATE = "UPDATE";
	private static final String OPERATION_DELETE = "DELETE";
	private static final String OPERATION_EXIT = "EXIT";
	private static final String OPERATION_BREAK = "BREAK";
	
	//实例女神对象
	private Goodess goodess = new Goodess();
	//实例action控制器对象
	private GoodessAction action = new GoodessAction();
	
	//查询女神条件
	private List<Map<String,Object>> queryParams = new ArrayList<Map<String,Object>>();
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(CONTXT);
		//键盘输入对象
		Scanner console = new Scanner(System.in);
		//实例女神对象
		Goodess goodess = new Goodess();
		//实例action控制器对象
		GoodessAction action = new GoodessAction();
		//记录历史标识
		String histroyFlag = null;
		//用户阶段
		int step = 0;

		
		View view = new View();
		
		//如果键盘有输入值的情况
		while(console.hasNext())
		{
			String in = console.next().toString();
			if( OPERATION_EXIT.substring(0, 1).equals(in.toUpperCase()) || OPERATION_ADD.substring(0, 1).equals(in.toUpperCase()) 
					|| OPERATION_UPDATE.substring(0, 1).equals(in.toUpperCase()) || OPERATION_DELETE.substring(0, 1).equals(in.toUpperCase()) 
					|| OPERATION_QUERY.substring(0, 1).equals(in.toUpperCase()) || OPERATION_GET.substring(0, 1).equals(in.toUpperCase()))
			{
				step = 0;
				histroyFlag = null;
			}
			//退出
			if(OPERATION_EXIT.equals(in.toUpperCase()) || OPERATION_EXIT.substring(0, 1).equals(in.toUpperCase()))
			{
				System.out.println("您已成功退出" );
				break;
			//新增女神
			}else if(OPERATION_ADD.equals(in.toUpperCase())  || OPERATION_ADD.substring(0, 1).equals(in.toUpperCase()) || OPERATION_ADD.equals(histroyFlag))
			{
				histroyFlag = OPERATION_ADD;
				view.add(in, step);
			}
			//修改女神
			else if(OPERATION_UPDATE.equals(in.toUpperCase())  || OPERATION_UPDATE.substring(0, 1).equals(in.toUpperCase()) || OPERATION_UPDATE.equals(histroyFlag))
			{
				histroyFlag = OPERATION_UPDATE;
				view.update(in,step);
			}
			//删除女神
			else if(OPERATION_DELETE.equals(in.toUpperCase())  || OPERATION_DELETE.substring(0, 1).equals(in.toUpperCase()) || OPERATION_DELETE.equals(histroyFlag))
			{
				histroyFlag = OPERATION_DELETE;
				view.del(in,step);
			//查询女神信息
			}else if(OPERATION_QUERY.equals(in.toUpperCase())  || OPERATION_QUERY.substring(0, 1).equals(in.toUpperCase()) || OPERATION_QUERY.equals(histroyFlag))
			{
				histroyFlag = OPERATION_QUERY;
				view.query(in,step);
			//查询单个女神信息
			}else if(OPERATION_GET.equals(in.toUpperCase())  || OPERATION_GET.substring(0, 1).equals(in.toUpperCase()) || OPERATION_GET.equals(histroyFlag))
			{
				histroyFlag = OPERATION_GET;
				view.get(in, step);
			}
			
			step++;
		}
	}

	/**
	 * 新增女神
	 * @param String in 用户输入值
	 * @param Int step 调用方执行次数
	 */
	private void add(String in, int step)
	{
		if(0==step)
		{
			System.out.println("您输入女神姓名:");
		}
		if(1==step)
		{
			System.out.println("您输入的女神姓名是:"+in);
			goodess.setUser_name(in);
			System.out.println("请输入的女神的生日: 格式为：yyyy-MM-dd");
		}else if(2==step)
		{
			System.out.println("您输入的女神生日是:"+in);	
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date birthday = null;
			try {
				birthday = sdf.parse(in);
				goodess.setBirthday(birthday);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("输入格式有误,请重新输入生日:");
				step = 1;
			}
			try {
				goodess.setSex(5);
				action.add(goodess);
				System.out.println("女神新增成功!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(goodess.toString());
				System.out.println("女神新增失败!");
			}
		}
	}
	/**
	 * 删除女神
	 * @param String in 用户输入值
	 * @param Int step 调用方执行次数
	 */
	private void del(String in,int step)
	{	
		if(0==step)
		{
			System.out.println("您输入女神id：");
		}
		if(1==step)
		{
			//将用户输入的id转换为整型
			int id = Integer.valueOf(in);
			System.out.println("您输入的女神id是:"+id);
			try {
				action.del(id);
				System.out.println("删除女神成功！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("删除女神失败！");
			}
		}
		
	}
	
	/**
	 * 修改女神
	 * @param String in 用户输入值
	 * @param Int step 调用方执行次数
	 */
	private void update(String in, int step)
	{
		if(0==step)
		{
			System.out.println("您输入女神姓名:");
		}
		if(1==step)
		{
			System.out.println("您输入的女神姓名是:"+in);
			goodess.setUser_name(in);
			System.out.println("请输入的女神的生日: 格式为：yyyy-MM-dd");
		}else if(2==step)
		{
			System.out.println("您输入的女神生日是:"+in);	
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date birthday = null;
			try {
				birthday = sdf.parse(in);
				goodess.setBirthday(birthday);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("输入格式有误,请重新输入生日:");
			}
			System.out.println("请输入女神表主键id：");
		}else if(3==step)
			try {
				goodess.setId(Integer.valueOf(in));
				goodess.setSex(6);
				action.edit(goodess);
				System.out.println("女神修改成功!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(goodess.toString());
				System.out.println("女神修改失败!");
			}
		}
	
	/**
	 * 按条件查询女神
	 * @param String in 用户输入值
	 * @param Int step 调用方执行次数
	 */
	private void query(String in, int step)
	{
		if(0==step)
		{
			System.out.println("您输入要查询的女神姓名:");
		}else if(1==step)
		{
			System.out.println("您输入的女神姓名是:" + in);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("name", "user_name");
			map.put("rela", "like");
			map.put("value", "'%"+ in +"%'");
			queryParams.add(map);
			System.out.println("请输入要查询的女神性别:");
		}else if(2==step)
		{
			System.out.println("您输入的女神性别是:"+in);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("name", "sex");
			map.put("rela", "=");
			map.put("value", in);
			queryParams.add(map);
			List<Goodess> gs;
			try {
				gs = action.query(queryParams);
				//遍历查询的结果		
				for (Goodess goodess : gs) {
					System.out.println(goodess.getId() + ":" + goodess.getUser_name() + "-" + goodess.getAdd_time());		
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 查询单个女神
	 * @param String in 用户输入值
	 * @param Int step 调用方执行次数
	 */
	private void get(String in, int step)
	{
		if(0==step)
		{
			System.out.println("请输入要查询女神的id： ");
		}else if(1==step)
		{
			Integer id = Integer.valueOf(in);
			System.out.println("您输入的id是：" + id);
			try {
				Goodess gs = action.get(id);
				System.out.println("姓名 = " + gs.getUser_name() + ", 生日 = " + gs.getBirthday());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
