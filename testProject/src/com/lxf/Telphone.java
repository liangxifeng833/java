package com.lxf;
/*
 * 定义类练习
 */
public class Telphone {
	//定义属性，手机屏幕尺寸, 以下三个称为成员变量，如果不手动赋初值，java默认成员变量初值为０
	float screen;
	//cpu
	float cpu;
	//内存
	float mem;
	
	/*
	 * 构造方法与类名相同，如果不定义构造方法，那么程序在实例化的时候，系统会自动创建
	 * 与类名相同的无参构造方法，构造方法无返回值
	 */
	public Telphone()
	{
		 System.out.println("我是无参构造方法");
	}
	/*
	 * 构造方法的重载
	 */
	public Telphone(float screen, float cpu, float mem)
	{
		 System.out.println("我是有参构造方法，参数分别:"+screen+cpu+mem );
	}
	/*
	 * 自定义方法
	 */
	public void showConfig()
	{
		int age; //在定义局部变量后，如果不赋值就时候java是不允许的
		System.out.println("我是手机，我的配置是:");
		System.out.println("屏幕大小："+screen+" CPU:"+cpu+"内存:"+mem);
	}
	
	
	
}
