package com.lxf.third;
/*
 * 入口类, 测试抽象类和接口
 */
public class Initail {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//实例化普通手机对象
		Phone tel1 = new ComPhone();
		tel1.call();
		tel1.sendMsg();
		//实例化智能手机对象
		SmartPhone tel2 = new SmartPhone();
		tel2.call();
		tel2.sendMsg();
		//智能手机不仅具有打电话和发短信的功能，同时还具有玩游戏的功能
		tel2.playGame();
		
		//实例化Psp游戏机对象
		Psp p = new Psp();
		p.playGame();
		
		//通过接口的引用，调用实现了该接口的对象的方法
		IPlayGame ip1 = new SmartPhone();
		ip1.playGame();
		IPlayGame ip2 = new Psp();
		ip2.playGame();
		
		//通过匿名内部类的方式1实现接口
		IPlayGame  p3 = new IPlayGame()
		{
			@Override
			public void playGame() {
				// TODO Auto-generated method stub
				System.out.println("使用匿名内部类方式１实现接口");
			}
		};
		p3.playGame();
		//通过匿名内部类的方式2实现接口, 直接new接口
		new IPlayGame()
		{
			@Override
			public void playGame() {
				// TODO Auto-generated method stub
				System.out.println("使用匿名内部类方式２实现接口");
			}
		}.playGame();
	}

}
