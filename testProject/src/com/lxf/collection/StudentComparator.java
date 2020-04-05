package com.lxf.collection;

import java.util.Comparator;

/**
 * 临时比较两个对象接口测试
 * @author lxf
 *
 */

public class StudentComparator implements Comparator<Student> {

	@Override
	public int compare(Student arg0, Student arg1) {
		// TODO Auto-generated method stub
		//使用当前学生对象的name和比较参数学生对象的name比较
		return arg0.name.compareTo(arg1.name);
	}

}
