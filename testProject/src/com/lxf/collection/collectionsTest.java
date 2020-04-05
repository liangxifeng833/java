package com.lxf.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/*
 * 测试集合框架的排序类
 */
public class collectionsTest {
	List<Integer> integerList = new ArrayList<Integer>();
	/*
	 * 通过Collection.sort()方法，对Integer泛型的List进行排序；
	 * 创建一个Integer泛型的List,插入是个100以内的不重复随机整数
	 * 调用Collection.sort()方法进行排序,不能使用基本类型作为泛型，如果是基本类型则使用包装类代替
	 */
	public void testSort1()
	{
		
		Random random = new Random();
		Integer k;
		//生成10个100以内的随机整数
		for(int i=0; i<10; i++)
		{			 
			do{
				k = random.nextInt(100);	
			}while(integerList.contains(k));
			integerList.add(k);
			System.out.println("成功添加整数："+k);
		}
		System.out.println("---------------排序前-------------------");
		for (Integer inte : integerList) {
			System.out.println(inte);
		}
		//排序
		Collections.sort(integerList);	
		System.out.println("---------------排序后-------------------");
			
		for (Integer inte : integerList) {
			System.out.println(inte);
		}
	}
	/*
	 * 对String泛型的List进行排序
	 * 创建String泛型的list,添加三个乱序的String元素
	 * 调用Collection.sort()方法进行排序,不能使用基本类型作为泛型，如果是基本类型则使用包装类代替
	 */
	public void testSort2()
	{
		List <String> stringList = new ArrayList<String>();
		stringList.add("zhangsan");
		stringList.add("lisi");
		stringList.add("1wangwu");
		System.out.println("---------------排序前-------------------");
		for (String inte : stringList) {
			System.out.println(inte);
		}
		//排序
		Collections.sort(stringList);	
		System.out.println("---------------排序后-------------------");
			
		for (String inte : stringList) {
			System.out.println(inte);
		}
	}
	/*
	 * 比较两个对象有两个接口
	 * Comparable -- 默认比较规则
	 *     实现该接口表示：这个类的实例可以比较大小，可以进行自然排序
	 *     定义了默认的比较规则
	 *     实现类需实现compareTo()方法
	 *     compareTo()方法返回正数表示大，负数表示小，0表示相等
	 * Comparator -- 临时比较规则，比较工具接口
	 *     用于定义临时比较规则，而不是默认比较规则
	 *     实现类要实现 compare()方法
	 *     Comparator和Comparable都是Java集合框架的成员
	 *     
	 *  以下对其他泛型的List进行排序，以Stuent泛型为例
	 * 
	 */
	public void testSort3()
	{
		List<Student> stList = new ArrayList<Student>();
		Random random = new Random();
		stList.add(new Student(random.nextInt(1000)+"","xiaoming"));
		stList.add(new Student(random.nextInt(1000)+"","honghong"));
		stList.add(new Student(random.nextInt(1000)+"","lanlan"));
		System.out.println("---------------排序前-------------------");
		for (Student student : stList) {
			System.out.println("学生：" + student.id+ ":" + student.name);
		}
		Collections.sort(stList);
		System.out.println("---------------根据字符串类型id排序后-------------------");
		for (Student student : stList) {
			System.out.println("学生：" + student.id+ ":" + student.name);
		}
		Collections.sort(stList,new StudentComparator());
		System.out.println("---------------根据学生姓名排序后-------------------");
		for (Student student : stList) {
			System.out.println("学生：" + student.id+ ":" + student.name);
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		collectionsTest ct = new collectionsTest();
		//ct.testSort1();
		//ct.testSort2();
		ct.testSort3();

	}

}
