package com.lxf.second;
/*
 * 定义动物类
 */
public class Animal {
	public int age = 10;
	public String name;
	
	 // 构造方法
	public Animal()
	{
		System.out.println("Animal类执行了");
	}
	 //吃方法
	public void eat()
	{
		System.out.println("动物具体吃东西的能力");
	}
}
