package com.lxf.collection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Set 是元素无序并且不可以重复的集合，被称为集
 * HashSet 哈希集，是Set的一个重要实现类
 * @author lxf
 *
 */

public class SetTest {
	//被选课程列表
	public List <Course> courseToSelect;
	//键盘输入对象
	private Scanner console;
	public Student student;
	/*
	 * 构造器
	 */
	public SetTest(){
		this.courseToSelect = new ArrayList<Course>();
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
		
		//并通过调用add方法，想list的指定位置添加元素
		Course cr2 = new Course("2","C语言");
		courseToSelect.add(0, cr2); //在list集合的指定的0位置添加对象，此时原0位置的对象会被挤到1位置
		
		Course cr3 = new Course("3","php语言");
		courseToSelect.add(2, cr3); //在list集合的指定的2位置添加对象
		//用addAll方法，向list序列集合中以数组的形式添加两个元素
		Course[] course = {new Course("4","离散数学"), new Course("5", "汇编语言")};
		courseToSelect.addAll(Arrays.asList(course)); //将课程数组course转换为list，添加到courseToSelect备选课程容器中
	}
	/**
	 * 通过for　each方法访问集合元素
	 */
	public void testForEach()
	{
		System.out.println("有如下课程待选(通过foreach访问)：");
		for(Course cr: this.courseToSelect)
		{
			System.out.println("课程："+cr.id + ":" + cr.name);
		}
	}
	/**
	 * 创建学生对象并且选课
	 */
	public void createStudentAndSelectCourse()
	{
		//创建一个学生对象
		student = new Student("1","小明");
		System.out.println("欢迎学生：" + student.name + "选课");
		//创建一个Scanner对象，用来接收从键盘输入的课程ID
		Scanner console = new Scanner(System.in);
		for(int i=0; i<3; i++)
		{
			System.out.println("请输入课程ID：");
			//接收键盘输入的课程id
			String courseId = console.next();
			//循环备选课程列表
			for (Course cr : courseToSelect) {
				//判断如果输入的id与备选课程列表中的id相等，则将该课程对象添加到学生对象的课程属性set的集合中
				if(cr.id.equals(courseId))
				{
					student.course.add(cr);
					/*
					 * Set中，添加某个对象，无论添加多少次，
					 * 最终只会保留一个对象（的引用），并且，保留的是第一次添加的那一个
					 */
//					student.course.add(cr);
				}
			}
		}
	}
	/**
	 * 测试Set的contains方法
	 * Set的contains原理是：在调用set的contains时候，其实是遍历set的每个元素
	 * 并且调用每个元素对象的hashCode()方法，如果hashCode值相等的时候，在调用每个元素对象的equals
	 * 只有在hashCode()和equals()全部返回true的时候，才认定该hashSet包含某一个元素
	 * 所以以下使用student.course.contains时候，需要在Course.java课程类中重写hashCode()和equals()方法
	 * 打开Course.java文件，使用Eclipse编辑器的Source->Generate hashCode And equals() 为为我们自动生成这两个方法
	 */
	public void testSetContains()
	{
		System.out.println("请输入已选课程ID：");
		String cId = console.next();
		System.out.println("请输入学生已选的课程名称:");
		String cName = console.next();
		//新创建一个课程对象
		Course course2 = new Course(cId,cName);
		System.out.println("已选课程是否包含新建对象:" + course2.name + "," + student.course.contains(course2));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SetTest st2 = new SetTest();
		//添加备选课程列表
		st2.testAdd();
		//循环遍历备选课程
		st2.testForEach();
		//穿件学生对象并选课
		st2.createStudentAndSelectCourse();
		//判断学生已选课程中是否包含输入的学生
		st2.testSetContains();
	}
	/**
	 * foreach方式循环遍历输出学生所选择的所有课程
	 */
	public void testForEachForSet(Student st)
	{
		System.out.println("共计选择了：" + st.course.size() + " 门课程；");
		for (Course cr : st.course) {
			System.out.println("学生所选择的课程是: " + cr.id + " : " + cr.name );
		}
	}

}
