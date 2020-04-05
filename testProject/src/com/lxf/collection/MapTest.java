package com.lxf.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

/**
 * map测试，map的<key value>是entry类对象的实例
 * @author lxf
 *
 */

public class MapTest {

	//定义用来承装学生类型的对象
	public Map<String,Student> students;
	//构造器中初始化students属性
	public MapTest()
	{
		this.students = new HashMap <String,Student> ();
	}
	/**
	 * 测试添加：输入学生ID,判断是否被占用
	 * 若未被占用，则输入姓名，创建新学生对象，并且添加到students中
	 */
	public void testPut()
	{
		//创建一个Scanner对象，用来活取输入的学生ID和姓名
		Scanner console = new Scanner(System.in);
		int i = 0;
		while(i < 3)
		{
			System.out.println("请输入学生ID:");
			String ID = console.next();
			//判断该ID是否被占用
			Student st = students.get(ID);
			if(st == null)
			{
				//提示输入学生姓名
				System.out.println("请输入学生姓名：");
				String name = console.next();
				//创建新学生对象
				Student newStudent = new Student(ID,name);
				//通过调用student的put方法，添加ID-学生映射
				students.put(ID, newStudent);
				System.out.println("成功添加学生：" + students.get(ID).name);
				i++;
			}else
			{
				System.out.println("该学生ID已经被占用;");
				continue;
			}
		}
	}
	/**
	 * 测试Map的KeySet方法
	 */
	public void testKeySet()
	{
		//通过KeySet方法，返回Map中的所有　“键”　的集合
		Set<String> keySet = students.keySet();
		//取得students容量
		System.out.println("总计有：" + students.size() + "个学生");
		//遍历KeySet, 取得每一个键，再调用get方法取得每个键对应的value
		for (String stuId : keySet) {
			Student st = students.get(stuId);
			if(st != null)
			{
				System.out.println("学生：" + st.name);
			}
		}
	}
	/**
	 * 测试删除map中的映射
	 */
	public void testRemove()
	{
		//创建一个Scanner对象，用来活取输入的学生ID
		Scanner console = new Scanner(System.in);
		while(true)
		{
			System.out.println("请输入要删除的学生ID:");
			String ID = console.next();
			//判断ID是否有对应的学生对象
			Student st = students.get(ID);
			if(st == null)
			{
				System.out.println("输入的ID " + ID +"不存在！");
				continue;
			}
			students.remove(ID);
			System.out.println("成功删除学生 " + st.name);
			break;
		}
		
	}
	/**
	 * 通过entrySet方法遍历Map
	 */
	public void testEntrySet()
	{
		//通过entrySet方法，返回Map中的所有键值对
		Set <Entry<String,Student>> entrySet = students.entrySet();
		for (Entry<String, Student> entry : entrySet) {
			System.out.println("获取键：" + entry.getKey());
			System.out.println("对应的值为：" + entry.getValue().name);
		}
	}
	/**
	 * 通过put修改测试
	 */
	public void testModify()
	{
		System.out.println("请输入要修改的学生ID:");
		//创建一个Scanner对象，用来活取输入的学生ID
		Scanner console = new Scanner(System.in);
		while(true)
		{
			//取得从键盘输入的学生ID
			String stuId = console.next();
			//从student中查找该学生ID对应的学生对象
			Student student = students.get(stuId);
			if(student == null)
			{
				System.out.println("输入的ID不存在！请重新输入：");
				continue;
			}
			System.out.println("请输入新的学生姓名：");
			//取得从键盘输入的学生姓名
			String stuName = console.next();
			Student st = new Student(stuId,stuName);
			students.put(stuId, st);
			System.out.println(" 修改成功！");
			break;
		}
		
	}
	/**
	 * 测试Map中是否包含某个Key和Value值
	 */
	public void testContainsKeyOrValue()
	{
		System.out.println("请输入要查询的学生ID:");
		//创建一个Scanner对象，用来活取输入的学生ID
		Scanner console = new Scanner(System.in);
		//使用map的containsKey()方法判断用户输入的id是否存在学生映射表中
		String id = console.next();
		boolean isContainKey = this.students.containsKey(id);
		System.out.println("您输入的ID为：" + id + "在学生映射对象表中是否存在：" + isContainKey);
		if(isContainKey)
		{
			System.out.println("对应的学生为：" + this.students.get(id).name);
		}
		
		//使用map的containsValue()方法判断用户输入的姓名是否存在学生映射表中
		System.out.println("请输入要查询的学生姓名:");
		String name = console.next();
		//新创建学生对象
		Student st = new Student(null,name);
		boolean isContainValue = this.students.containsValue(st);
		if(isContainValue)
		{
			System.out.println("在学生映射表中确实存在学生：" + name);
		}else
		{
			System.out.println("该学生：" + name + "在学生映射表中不存在!");
		}
		
		
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MapTest mt = new MapTest();
		mt.testPut();
//		mt.testKeySet();
//		mt.testRemove();
		mt.testEntrySet();
//		mt.testModify();
//		mt.testEntrySet();
		mt.testContainsKeyOrValue();
	}

}
