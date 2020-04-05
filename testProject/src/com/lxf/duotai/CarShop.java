package com.lxf.duotai;
/*
 * 多态的实例，多态就是一种类型（都是Car类型）表现出多种状态
 * （宝马汽车的名称是BMW，售价是500000；奇瑞汽车的名称是CheryQQ，售价是10000）
 * 多态给我们带来的好处是消除了类之间的耦合关系，使程序更容易扩展。
 * 比如在上例中，新增加一种类型汽车的销售，只需要让新定义的类继承Car类并实现它的所有方法，
 * 而无需对原有代码做任何修改，CarShop类的sellCar(Car car)方法就可以处理新的车型了
 * 汽车4S点类
 */
public class CarShop {
	//汽车销售总价
	private int totalPrice;
	//卖出一辆车
	public void sellCar(Car car)
	{
		System.out.println("车型：" +car.getName()+" 单价："+car.getMoney());
		//计算总价
		totalPrice+= car.getMoney();
	}
	//总价
	public int getPrice()
	{
		return totalPrice;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//实例化汽车4ｓ店对象
		CarShop shop = new CarShop();
		//实例化宝马车对象
		Car BMW = new Bmw();
		//卖出一辆宝马
		shop.sellCar(BMW);
		//实例化qq车对象
		Car qq = new CheryQQ();
		//卖出一辆qq
		shop.sellCar(qq);
		
		//汽车总计销售额
		System.out.println("总计汽车销售额为: " + shop.getPrice());
		
	}

}
