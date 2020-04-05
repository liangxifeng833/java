package com.lxf.second;
/*
 * 入口初始化类
 */
public class Initail {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//创建狗的对象dog的值是一个内存地址，并不是实际的对象
		Dog  dog = new Dog();
		dog.age = 10;
		dog.name = "狗";
		dog.eat();
		//创建狗的对象dog的值是一个内存地址，并不是实际的对象，此时dog和dog2代表两个内存地址，如果想比较两个实例化的对象是否相同，
		//则需要在dog类中重写Object类的equals方法
		Dog dog2 = new Dog();
		dog2.age = 10;
		dog2.name = "狗";
		if(dog.equals(dog2))
		{
			System.out.println("两个对象是相同的");
		}else
		{
			System.out.println("两个对象是不同的");
		}
		
		//访问dog类中的method, 在method方法中使用super关键字访问父类中的age属性
		dog.method();
	    //直接输出对象，如果在dog类中不重写toString方法则输出的是一个hash算法过的字符串，代表对象的内存地址，否则输出dog类的属性值
		System.out.println("直接输出dog对象:"+dog);
		
		//多态的测试
		Animal obj1 = new Animal(); //父类引用指向本类
		Animal obj2 = new Dog();       //父类引用指向子类对象
		//Dog obj3 = new Animal();    //错误的写法
		obj1.eat();
		obj2.eat();
		

	}

}
