package com.lxf.third;
/*
 * 智能手机类，继承抽象类Phone,并实现了IPlayGame玩游戏的接口
 */

public class SmartPhone extends Phone implements IPlayGame{

	@Override
	public void call() {
		// TODO Auto-generated method stub
		System.out.println("我是高级智能手机，有打电话的功能");

	}

	@Override
	public void sendMsg() {
		// TODO Auto-generated method stub
		System.out.println("我是高级智能手机，有发短信的功能");
	}

	@Override
	public void playGame() {
		// TODO Auto-generated method stub
		System.out.println("我是智能手机，具有玩游戏的功能");
	}

}
