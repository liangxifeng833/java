package com.lxf.collection;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试泛型类
 * １．泛型规定了某个集合可以存储特定类型的对象，会在编译期间进行类型检查
 * ２．可以直接指定类型获取集合元素
 * ３．泛型集合中不能添加泛型规定的类型及其子类型以外的对象
 * ４．泛型集合可以存储泛型类型和泛型子类型的对象实例
 * ５．泛型集合中的限定类型：
 *          不能使用基本类型（int / long / boolean），可以使用包装类限定允许存入的基本数据类型
 * @author lxf
 *
 */
public class testGeneric {
	/**
	 * 带有泛型-Course, 的List类型属性
	 */
	public List <Course> courses;
	/**
	 * 泛型不能使用基本类型
	 */
	//List <int> list = new ArrayList <int> (); 错误写法，应改为以下写法
	List <Integer> listNew = new ArrayList <Integer> ();
	//System.out.println();
	/**
	 * 构造器
	 */
	public testGeneric()
	{
		this.courses = new ArrayList <Course> ();
	}
	/**
	 * 测试添加
	 */
	public  void testAdd()
	{
		Course cr1  = new Course("1","大学语文");
		courses.add(cr1);
		//泛型集合中不能添加泛型规定的类型及其子类型以外的对象
		//courses.add("添加一些奇怪的东西！");
		Course cr2  = new Course("2","java课程");
		courses.add(cr2);
		listNew.add(1);
		System.out.println("基本类型必须使用包装类："+this.listNew.get(0));
	}
	/**
	 * 测试循环遍历
	 */
	public void testForeach()
	{
		for(Course cr: courses){
			System.out.println(cr.id+" : " + cr.name);
		}
	}
	/**
	 * 泛型集合可以添加泛型子类型对象的实例
	 */
	public void testChild()
	{
		ChildCourse ccr = new ChildCourse();
		ccr.id = "3";
		ccr.name = "我是子类型的课程对象实例";
		courses.add(ccr);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testGeneric tg = new testGeneric();
		tg.testAdd();
		tg.testChild();
		tg.testForeach();
	}
}
