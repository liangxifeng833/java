package com.lxf.third;
/*
 * 普通手机类，继承抽象类Phone类
 */

public class ComPhone extends Phone {

	@Override
	public void call() {
		// TODO Auto-generated method stub
		System.out.println("我是普通功能手机，有打电话的功能");

	}

	@Override
	public void sendMsg() {
		// TODO Auto-generated method stub
		System.out.println("我是普通功能手机，有打发短信的功能");
	}

}
