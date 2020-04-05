package com.lxf;
import java.util.Arrays;
//java中在不同的包下可以存在相同的类, 默认导入该文件所在包的所有类文件, import com.lxf.*
//import com.lxf.second.Telphone;

/*
 * java练习二 
 * @author  liangxifeng
 * date 2016-03-14
 */

/*
 * #### ######################## java的封装特性 ######################################
 * 1. java中访问修饰符的访问范围
  *    private : 		 本类　            			
 *    默认：   		 本类　同包　				
 *    protected 　本类    同包  子类　
 *    public         　本类    同包  子类 　其他 　
 *   java中 this 关键字作用和php中一样，都代表本对象，只是使用的时候没有$符号，比如：this.name;
 *   java中static关键字 修饰的成员称为静态成员或类成员，下面有详细介绍
 * 
 * 2.  Java 中的内部类
 *      定义在另外一个类里面的类。与之对应，包含内部类的类被称为外部类, 内部类的主要作用如下：
 *      (1) 内部类提供了更好的封装，可以把内部类隐藏在外部类之内，不允许同一个包中的其他类访问该类
 *      (2) 内部类的方法可以直接访问外部类的所有数据，包括私有的数据
 *      (3)  内部类所实现的功能使用外部类同样可以实现，只是有时使用内部类更方便
 *      内部类分为：成员内部类，静态内部类，方法内部类，匿名内部类
 *      外部类是不能直接使用内部类的成员和方法滴
 *      
 * 3. 静态内部类， static 修饰的内部类
 * 　静态内部类不能直接访问外部类的非静态成员，但可以通过 new 外部类().成员 的方式访问 
 * 　如果外部类的静态成员与内部类的成员名称相同，可通过“类名.静态成员”访问外部类的静态成员；
 * 　如果外部类的静态成员与内部类的成员名称不相同，则可通过“成员名”直接调用外部类的静态成员
 * 　创建静态内部类的对象时，不需要外部类的对象，可以直接创建 内部类 对象名= new 内部类();
 * 
 * 4. 方法内部类就是内部类定义在外部类的方法中，方法内部类只在该方法的内部可见，即只在该方法内可以使用。
 *     由于方法内部类不能在外部类的方法以外的地方使用，因此方法内部类名不能使用访问控制符和 static 修饰符。
 *     
 * #### ######################## java的继承特性 ######################################
 * 1. 继承关键字 extends 具体实例在com.lxf.sencod包中
 *     继承的初始化顺序是：先初始化父类，再初始化子类，也就是说在创建子类对象的时候，父类对象先创建（创建完毕后实际有两个对象）
 * 2. final 关键字的使用：
 * 	(1)  可以修饰类，方法，属性和变量
 * 　(2)  修饰类的时候，代表该类不可被继承
 * 　(3)  修饰方法的时候，代表该方法不能被重写
 * 	 (4)  修饰属性的时候，类的初始属性必须有默认值，或在构造方法中赋值，但是两种只能选择其一
 * 　(5)  修饰变量的时候，该变量只能被赋值一次，也就是常量的定义方法
 * 3. super 关键字
 *    (1) 在对象的内部使用，可以代表父类对象
 *    (2) 访问父类属性：super.age,  方法父类方法：super.eat()
 *    (3) 在子类的构造方法中 系统隐式自动为我们先调用父类的无参构造方法，也就是相当于php中的parent::__consurt();
 *    (4) 如果要手动显式的调用父类构造方法，必须在子类构造方法的第一行　super()
 * 4. Object 类
 * 　(1) 是所有类的父类，如果一个类没有使用extends关键字明确标识继承另外一个类，那该类默认继承Object类 
 * 	 (2) Object中的方法适合于所有子类
 * 	 　  toString() 方法，如果子类不重写父类的toString(),则直接输出对象的话，则该方法返回hash编码后的字符串，代表该对象在内存中的位置，比如：com.lxf.second.Dog@10c2cfe1
 *                      如果子类重写toString(); 则可以输出子类的属性值
 * 		　equals() 方法，比较对象引用是否指向同一块内存地址，该方法返回值是一个boolean
 *                     dog = new Dog(); //dog其实是一个内存地址，指向具体对象
 *                     dog2 = new Dog(); //new了两个对象，也就是在内存中开辟了两个内存空间， dog 和 dog2是两个内存地址肯定是不相等的
 *                     一般情况下比较两个对象时是比较他们的值是否一致，所以要进行重写,在eclipse编辑器中选择：Sourse->generate hashCode and equals,
 *                     编辑器会自动将equals方法重写好
 * 
 *  #### ######################## java的多态 ######################################
 *  1. 继承是多态的基础
 *  2. 多态分为：引用的多态和方法的多态
 *      (1) 引用的多态：父类的引用既可以指向本类，也可以指向子类的对象；　比如：Animal obj1 = Animal();父类引用指向本类，Animal obj2 = Dog(); 父类引用指向子类对象 
 *      (2) 方法的多态：父类引用子类对象后，调用子类的方法；
 *  3. 多态中引用类型的转换，instanceof　关键字避免类型转换的安全性
 *       Dog dog = new Dog();
 *       Animal animal = dog;  //向上类型转换　自动类型转换
 *       if(animal instanceof Dog)
 *       {
 *       		Dog dog2 = (Dog)animal; //强制类型转换为Dog类型
 *       }else
 *       {
 *       		System.out.println("无法进行类型转换");
 *       }
 * 4. 抽象类
 *     public abstract class Name
 *     {
 *         零个到多个抽象方法
 *         零个到多个普通方法
 *         零个到多个普通变量或常量
 *     }
 *     父类知道总计有几个方法，但无法准确知道子类的这几个方法具体如何实现
 *     是对类的抽象，目的是约束子类必须有那些特征，不关注子类的细节
 *     使用规则：　
 *             abstract 定义抽象类；
 *             abstract 定义抽象方法, 只有声明，不需要实现;
 *             包含抽象方法的类是抽象类
 *             抽象类中也可以包含普通的方法
 *             抽象类不能直接创建，可以定义引用变量
 *             
 * 5. 接口　
 *      public interface name [ extends 父接口１，父接口２]
 *      {
 *          零个到多个常量的定义...
 *          零个到多个抽象方法的定义
 *      }
 *     类是一种具体的实现体，而接口定义了某一批类所需要遵守的规范；
 *     接口不关心这些类的内部数据，也不关心这些类里方法的细节
 *     它只规定这些类里必须提供某些方法
 *     接口一般用来被继承，被实现的，修饰符一般建议用public　
 *     不能使用private , protected 修饰接口
 *     类是单继承的，接口可以多实现
 *     接口的属性都是常量，所以需要 public static final 修饰符，如果定义时候不加修饰符，系统会自动加上（方法也是一样的道理）
 *     可以描述两个不同对象之间的共同特性，并通过接口的引用使用它们
 *     public class 类名　extends 父类　implements 接口１,接口２
 *     {
 *     	如果继承了抽象类，则需要实现被继承的所有抽象方法；当然也要实现接口中的所有抽象方法
 *     }
 *     此外，接口还在使用过程中，还经常与匿名内部类配合使用，匿名内部类就是没有名字的内部类，多用于关注实现而不关注实现类的名称
 *     Interface i = new Interface()
 *     {
 *     	public void method()
 *     	{
 *     		System.out.println("匿名内部类实现接口的方式");
 *     	}
 *     }
 */
public class practice2 {
	double score1 = 90;
	static double score2=99;
	private int score3 = 100; //私有变量，如果在类的外部访问时，需要调用类的方法
	private String name = "liangxifeng";
	final public int imprest = 500; //常量的定义, 不允许被改变
	
	/*
	 * 访问类中的私有属性，需要方法间接访问，
	 * 为了标准通常访问私有属性的方法命名规则为get+属性名(首字母大写)
	 */
	public int getScore3()
	{
		return score3;
	}
	/*
	 * 修改类中的私有属性，需要方法间接修改，
	 * 为了标准通常修改私有属性的方法命名规则为set+属性名(首字母大写)
	 */
	public void setScore3(int newScore)
	{
		 this.score3 = newScore;
	}
	/*-------------------------------- java 方法的使用---------------------------------------------------*/
	// 访问修饰符　返回值类型　方法名（参数列表）｛方法体｝
	//方法返回值的类型，如果方法不返回任何值，则返回值类型指定为 void ( 也就是无参无返回值方法 )
	public void print()
	{
		System.out.println("Hello World");
	}
	//无参带返回值方法,返回值为整型
	public int getNum()
	{
		int a=5;
		int b=12;
		int sum = a+b;
		return sum; //有返回值必须用return, 如果方法的返回值是void 则不允许在方法内使用return
	}
	//有参数有返回值的方法, 调用带参方法时，必须保证实参的数量、类型、顺序与形参一一对应
	public void printArr(int[] scores)
	{
		//使用Arrays类toAstring方法将数组转换为字符串输出
		System.out.println(Arrays.toString(scores));
	}
	public double calcAvg(double score1, double score2)
	{
		double res =  (score1+score2)/2;
		 return res;
	}
	
	/*
	 * java 中方法的重载
	 * １，必须是在同一个类中
	 * ２， 方法名相同
	 * ３，方法参数的个数、顺序或类型不同
	 * ４，与方法的修饰符或返回值没有关系　
	 *  ５，当调用被重载的方法时， Java 会根据参数的个数和类型来判断应该调用哪个重载方法，
	 *  　　参数完全匹配的方法将被执行。
	 */
	public void show()
	{
		System.out.println("无参的show方法");
	}
	public void show(String name)
	{
		System.out.println("带有一个字符串参数的show方法，参数值为"+name);
	}
	public String show(String name, int age)
	{
		System.out.println("带有两个字符串参数的show方法，参数值为"+name+age);
		return name+age;
	}
	/*
	 * 功能：创建指定长度的int型数组，并生成100以内随机数为数组中的每个元素赋值
	 * 定义一个带参带返回值的方法，通过参数传入数组的长度，返回赋值后的数组
	 */
	public int[] getArray(int length)
	{
		int [] nums = new int[length];
		for(int i = 0; i < nums.length; i++)
		{
			nums[i] = (int)(Math.random()*100); //100以内的随机数
		}
		return nums;
	}
	/*-------------------------------- Java 中的 static 关键字使用之静态变量-----------------------------------------------------------------------*/
	/*
	 *  static 修饰的成员称为静态成员或类成员
	 *  它属于整个类所有，而不是某个对象所有,即被类的所有对象所共享
	 * 静态成员可以使用类名直接访问，也可以使用对象名进行访问
	 * 使用 static 可以修饰变量、方法和代码块
	 *  静态方法中可以直接调用同类中的静态成员，但不能直接调用非静态成员，
	 *  如果希望在静态方法中调用非静态变量，可以通过创建类的对象，然后通过对象来访问非静态变量
	 *  在普通成员方法中，则可以直接访问同类的非静态变量和静态变量
	 */
	//使用 static 声明静态变量
	static  String className = "java 开发一班";
	//使用 static 声明静态方法
	public  static   void print2()
	{
		/*
		practice2 test = new practice2();
		System.out.println("在静态方法中访问非静态方法："+test.score1);
		System.out.println("在静态方法中访问静态方法："+practice2.score2);
		*/
	}
	/*
	 *  在类的声明中，可以包含多个初始化块，当创建类的实例时，就会依次执行这些代码块。
	 *  静态初始化块只在类加载时执行，且只会执行一次，同时静态初始化块只能给静态变量赋值，
	 *  不能初始化普通的成员变量。
	 */
	{ //普通初始化块，
		score1 = 1222222;
		score2 = 133333;
		System.out.println(score1+score2);
	}
	static { //静态初始化块，该初始化在内存中的执行顺序要先于以上普通初始化块，只能给静态变量赋值
		score2 = 12333;
		System.out.println(score2);
	}
	public static void main(String[] args) {
		practice2 test = new practice2();
		//调用test方法
		test.print();
		//调用getNum方法
		int num = test.getNum();
		System.out.println(num);
		//调用printArr方法
		int[]  scores = {84,91,74,62};
		test.printArr(scores); 
		double avgScore = test.calcAvg(94,81);
		System.out.println("成绩的平均分为"+avgScore);
		
		//重载调用
		test.show();
		test.show("liangxifeng");
		test.show("liangxifeng", 20);
		
		//练习
		// 调用方法并将返回值保存在变量中
		int nums[] = test.getArray(10);
		// 将数组转换为字符串并输出
		System.out.println(Arrays.toString(nums));
		
		//实例化Telphone类为对象, 实例化其他文件中的类，注意与php不同点，不需要include文件
		Telphone	 phone = new  Telphone();
		phone.screen = 15.2f;
		phone.cpu = 1.4f;
		phone.mem = 2.5f;
		phone.showConfig();
		
		//实例化Telphone类为对象, 使用构造方法实现初始化操作
		Telphone phone2 = new Telphone();
		//实例化Telphone类为对象, 使用构造方法实现初始化操作,　并传递参数，使用重载的有参构造方法
		Telphone phone3 = new Telphone(18.2f,1.5f, 2.8f);	
		
		// 访问静态变量，输出班级名称
		System.out.println(practice2.className);
		//使用类名访问静态方法
		practice2.print2();
		// 使用变量名访问静态方法
		test.print2();
		
		//实例化内部类
		Inner i = test.new Inner();
		//访问内部类的方法
		i.iShow();
		
		// 直接创建内部静态类的对象
        SInner si = new SInner();
        // 调用show方法
		si.show();
		
		//调用外部类的outM方法
		test.outM();
	}
	
	//成员内部类的创建（普通内部类）
	public class Inner
	{
		//定义和外部类同样的属性名
		double score1 = 9;
		public void iShow()
		{
			System.out.println("访问外部类中的score1="+practice2.this.score1);
			System.out.println("访问内部类中的score1="+this.score1);
		}
	}
	 //创建静态内部类
	public   static   class SInner
	{
        // 内部类中的变量score
        double score2 = 91;
		public void show() {
			System.out.println("访问外部类中的score2：" +practice2.score2);
			System.out.println("访问内部类中的score2：" + this.score2);
		}
	}
	//定义外部类方法
	public void outM()
	{
		//定义方法内部类
		class MInner
		{
			private int score = 83;
			public int getScore()
			{
				return score+10;
			}
		}
		//创建方法内部类对象
		MInner mi = new MInner();
		//调用内部类的方法
		int newScore = mi.getScore();
		System.out.println("姓名："+this.name+"\n方法内部类加分后的值为："+newScore);
	}
	
	
}
