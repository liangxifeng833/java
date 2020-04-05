package com.atguigu.springmvc.crud.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.atguigu.springmvc.crud.entities.Department;
/**
 * 部门dao
 * @author lxf
 */
@Repository
public class DepartmentDao {

	private static Map<Integer, Department> departments = null;
	
	static{
		departments = new HashMap<Integer, Department>();
		
		departments.put(101, new Department(101, "D-AA"));
		departments.put(102, new Department(102, "D-BB"));
		departments.put(103, new Department(103, "D-CC"));
		departments.put(104, new Department(104, "D-DD"));
		departments.put(105, new Department(105, "D-EE"));
	}
	//获取所有部门信息
	public Collection<Department> getDepartments(){
		return departments.values();
	}
	//通过主键获取部门信息
	public Department getDepartment(Integer id){
		return departments.get(id);
	}
}
