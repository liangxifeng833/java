package com.lxf.third;
/*
 * 抽象电话类
 */
public abstract class Phone {
	
	//抽象方法，没有方法体，打电话
	public abstract void call();
	//抽象方法，没有方法体，发短信
	public abstract void sendMsg();
	
	//抽象类中也可以包含不同方法
	public void commonMethod()
	{
		System.out.println("抽象类中也可以包含普通方法");
	}

}
