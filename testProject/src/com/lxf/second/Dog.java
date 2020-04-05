package com.lxf.second;
/*
 * 定义狗类继承动物类使用关键字和php一样都是 extends
 */
public class Dog extends Animal {
	public int age = 20;
	//构造方法
	public   Dog()
	{
		super(); //系统隐式为我们先调用父类的Animal构造方法，也就是相当于php中的parent::__consurt();
		System.out.println("Dog 类执行了");
	}
	//对父类的eat方法进行重写，修饰符，返回值类型，参数列表个数必须与父类被重写的方法保持一致
	public void eat()
	{
		System.out.println("狗具有吃骨头的能力");
	}
	//定义method方法
	public void method()
	{
		System.out.println("使用super关键字访问父类的属性age = "+super.age);
	}
	//该方法是通过点击eclipse的source=>gengrate toString 生成，重写父类Object方法，用于判断两个对象值是否相同
	@Override
	public String toString() {
		return "Dog [age=" + age + "]";
	}
	//该方法是通过点击eclipse的source=>gengrate hashCode and equals生成，重写父类Object方法，用于判断两个对象值是否相同
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dog other = (Dog) obj;
		if (age != other.age)
			return false;
		return true;
	}
	
}
