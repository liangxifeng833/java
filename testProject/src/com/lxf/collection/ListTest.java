package com.lxf.collection;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;            //引入list集合接口
import java.util.ArrayList;  //引入list集合接口的实现类
import java.util.Scanner;

/**
 * 被选课程类
 * @author lxf
 *
 */
public class ListTest {
	//用于存放备选课程的list容器
	public List  courseToSelect;
	//键盘输入对象
	private Scanner console;
	public ListTest()
	{
		this.courseToSelect = new ArrayList();
		this.console = new Scanner(System.in);
	}
	
	/**
	 * 用于往courseToSelect中添加备选课程
	 */
	public void testAdd()
	{
		//创建一个课程对象, 并通过调用add方法，添加到备选课程list中
		Course cr1 = new Course("1","数据结构");
		courseToSelect.add(cr1);
		Course temp = (Course) courseToSelect.get(0);
		System.out.println("添加了课程1：" + temp.id + " : " + temp.name);
		
		//并通过调用add方法，想list的指定位置添加元素
		Course cr2 = new Course("2","C语言");
		courseToSelect.add(0, cr2); //在list集合的指定的0位置添加对象，此时原0位置的对象会被挤到1位置
		Course temp2 = (Course) courseToSelect.get(0);
		System.out.println("添加了课程2：" + temp2.id + " : " + temp2.name);
		
		Course cr3 = new Course("3","php语言");
		courseToSelect.add(2, cr3); //在list集合的指定的2位置添加对象
		Course temp3 = (Course) courseToSelect.get(2);
		System.out.println("添加了课程3：" + temp3.id + " : " + temp3.name);
		
		//用addAll方法，向list序列集合中以数组的形式添加两个元素
		Course[] course = {new Course("4","离散数学"), new Course("5", "汇编语言")};
		courseToSelect.addAll(Arrays.asList(course)); //将课程数组course转换为list，添加到courseToSelect备选课程容器中
		Course temp4 = (Course) courseToSelect.get(3);
		Course temp5 = (Course) courseToSelect.get(4);
		System.out.println("添加了两门课程：" + temp4.id + " : " + temp4.name + ";" + temp5.id + ":" + temp5.name);
		
		//用addAll方法，向list序列集合中添加指定位置元素, 代替以上刚刚添加 离散数学和汇编语言的位置，离散和汇编自动被挤到5,6位置
		Course[] course2 = {new Course("６","高等数学"), new Course("7", "大学英语")};
		courseToSelect.addAll(3, Arrays.asList(course2));
		Course temp6 = (Course) courseToSelect.get(3);
		Course temp7 = (Course) courseToSelect.get(4);
		System.out.println("添加了两门课程：" + temp6.id + " : " + temp6.name + ";" + temp7.id + ":" + temp7.name);
	}
	/**
	 * 获取List中所有元素
	 */
	public void testGet()
	{
		//获取list的长度
		int size = courseToSelect.size();
		System.out.println("有如下课程待选(通过list.size遍历访问)：");
		for(int i = 0; i < size; i++)
		{
			Course cr = (Course) courseToSelect.get(i);
			System.out.println("课程："+cr.id + ":" + cr.name);
		}
	}
	/**
	 * 通过迭代器来遍历 List
	 */
	public void testIterator()
	{
		Iterator it = courseToSelect.iterator();
		System.out.println("有如下课程待选(通过迭代器访问)：");
		while(it.hasNext())
		{
			Course cr = (Course) it.next();
			System.out.println("课程："+cr.id + ":" + cr.name);
		}
	}
	/**
	 * 通过for　each方法访问集合元素
	 */
	public void tesForEach()
	{
		System.out.println("有如下课程待选(通过foreach访问)：");
		for(Object obj: courseToSelect)
		{
			Course cr = (Course) obj;
			System.out.println("课程："+cr.id + ":" + cr.name);
		}
	}
	/**
	 * 修改list中的元素
	 */
	public void testModify()
	{
		courseToSelect.set(4, new Course("8"," 毛概"));
	}
	/**
	 * 删除List中的元素
	 */
	public void testRemove()
	{
		Course cr = (Course) courseToSelect.get(4);
		System.out.println("我是课程："+ cr.id + cr.name + ", 我即将被删除");
		// 通过传入对象，删除List中元素
		courseToSelect.remove(cr);  //或直接使用索引位置删除：courseToSelect.remove(4);
		//删除索引下标为4和５位置的元素
		Course[] courses = {(Course) courseToSelect.get(4),(Course) courseToSelect.get(5)};
		courseToSelect.removeAll(Arrays.asList(courses));
		System.out.println("成功删除课程！");
		tesForEach();
	}
	/**
	 * 测试List中的contains方法
	 * 通过contains判断list中是否有某一个对象的时候，原理是：循环list的每一个对象，并通过调用每个对象的equals方法，
	 * 所以我们要在list包含的对象所在类型重写java顶层对象Object的 equals方法，如下操作：
	 * 打开Course类中，在eclipse编辑器中选择：Sourse->generate hashCode and equals
	 */
	public void testListContains()
	{
		//获取备选课程序列的第0个元素
		Course course = (Course)courseToSelect.get(0);
		System.out.println("取得课程：" + course.name);
		System.out.println("备选课程是否包含:" + course.name + "," + courseToSelect.contains(course));
		System.out.println("请输入课程ID：");
		String cId = console.next();
		System.out.println("请输入课程name：");
		String cName = console.next();
		//新创建一个课程对象
		Course course2 = new Course(cId,cName);
		System.out.println("备选课程是否包含新建对象:" + course2.name + "," + courseToSelect.contains(course2));
		//通过indexOf()方法来取得某元素第一次出现的索引位置，lastIndexOf()返回最后一个出现的索引位置
		//indexOf的实现机制是：循环遍历list中的每个对象，调用每个对象的equals方法，如果存在则返回对应索引位置, 否则返回-1
		if(courseToSelect.contains(course2))
		{
			System.out.println("课程：" + course2.name + "的在list中的索引位置是：" + courseToSelect.indexOf(course2));
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ListTest lt = new ListTest();
		lt.testAdd();
		lt.testListContains();
		//lt.testGet();
		/*
		lt.testIterator();
		lt.testModify();
		lt.tesForEach();
		*/
		//lt.testRemove();
	}
}
