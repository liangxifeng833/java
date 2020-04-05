package entity;
/*
 * 学生实体类（持久化类）
 */

import java.sql.Blob;
import java.util.Date;

public class Students {
	private int sid; // 学号
	private String sname; // 姓名
	private String gender; // 性别
	private Date birthday; // 生日
	private Address address; // 地址
	private Blob picture; //照片

	// 公有的无参构造方法
	public Students() {	
	}

	/**
	 * 带参数的构造方法，使用Sources工具生成的
	 */
	public Students(int sid, String sname, String gender, Date birthday) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.gender = gender;
		this.birthday = birthday;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Blob getPicture() {
		return picture;
	}

	public void setPicture(Blob picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "Students [sid=" + sid + ", sname=" + sname + ", gender=" + gender + ", birthday=" + birthday
				+ ", address=" + address + "]";
	}
}
