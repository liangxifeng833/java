package com.lxf.collection;
import java.util.Set;             //引入set集合接口
import java.util.HashSet;   //引入set集合接口的实现类

/**
 * 学生类
 * 本类implements Comparable<Student>目的为了解决类collectionsTest中testSort3()，
 * 对Student泛型的List进行排序
 * @author lxf
 */

public class Student implements Comparable<Student> {
	public String id;
	public String name;
	public Set<Course> course;
	public Student(String id, String name)
	{
		this.id = id;
		this.name = name;
		this.course = new HashSet<Course>(); //HashSet是Set接口的一个非常重要的实现类
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public int compareTo(Student arg0) {
		// TODO Auto-generated method stub
		//使用当前对象的id和比较参数对象的id比较
		return this.id.compareTo(arg0.id);
	}
	
}
