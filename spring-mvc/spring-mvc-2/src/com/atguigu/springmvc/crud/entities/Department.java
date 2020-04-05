package com.atguigu.springmvc.crud.entities;
/**
 * 部门实体类
 * @author lxf
 *
 */
public class Department {
    //部门id
	private Integer id;
	//部门名称
	private String departmentName;

	public Department() {
		// TODO Auto-generated constructor stub
	}
	
	public Department(int i, String string) {
		this.id = i;
		this.departmentName = string;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", departmentName=" + departmentName
				+ "]";
	}
	
}
