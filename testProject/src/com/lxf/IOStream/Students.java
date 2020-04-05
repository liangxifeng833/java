package com.lxf.IOStream;

import java.io.Serializable;
/**
 * Students学生实体类, 实现了Serializable接口,可以被序列化
 * @author lxf
 *
 */
public class Students implements Serializable {
	private int id;
	private String uname;
	//transient关键字,表明pass属性不会进行jvm的默认序列化, 也可以自己完成该元素的序列化
	private transient String pass;
	
	/*
	 * 自己完成被transient关键字修饰的属性序列化
	 */
	private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException
	{
		//把jvm能默认序列化的元素进行序列化操作
		s.defaultWriteObject();
		//自己完成pass的序列化
		s.writeObject(pass);
	}	
	/*
	 * 自己完成被transient关键字修饰的属性反序列化
	 */
	private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException
	{
		s.defaultReadObject();
		this.pass = (String) s.readObject();
	}
	
	public Students()
	{
	}
	
	public Students(int id, String uname, String pass) {
		super();
		this.id = id;
		this.uname = uname;
		this.pass = pass;
	}


	@Override
	public String toString() {
		return "Students [id=" + id + ", uname=" + uname + ", pass=" + pass
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
}
