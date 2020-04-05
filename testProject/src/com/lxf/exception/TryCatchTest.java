package com.lxf.exception;
/*
 * 异常处理测试
 */

public class TryCatchTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TryCatchTest tct = new TryCatchTest();
//		int result = tct.test();
//		System.out.println("test()方法执行完毕，返回值为："+result);
//		int result2 = tct.test2();
//		System.out.println("test2()方法执行完毕，返回值为result=："+result2);
		int result3 = tct.test3();

	}
	/*
	 * try-catch语句块的使用
	 * divider 除数
	 * result  结果
	 * try-catch 捕获while循环
	 * 每次循环，divider减一，result = result+100/divider
	 * 如果：捕获异常，打印输出“抛出了异常！！！”，返回-1
	 * 否则：返回result
	 */
	public int test()
	{
		int divider = 10;
		int result = 100;
		try{
			while(divider > -1)
			{
				divider--;
				result = result+100/divider;
			}
			return result;
		}catch(Exception e)
		{
			//输出具体异常信息
			e.printStackTrace();
			System.out.println("循环抛出异常了！！！");
			return -1;
		}
	}
	/*
	 * try-catch-finally语句块的使用
	 */
	public int test2()
	{
		int divider = 10;
		int result = 100;
		try{
			while(divider > -1)
			{
				divider--;
				result = result+100/divider;//除数不能为0,会抛出一个算数异常
			}
			return result;
		}catch(Exception e)
		{
			//输出具体异常信息
			e.printStackTrace();
			System.out.println("循环抛出异常了！！！");
			return result=999;
		}finally{ //在try-catch的返回值和被调用者之间执行finally语句块
			System.out.println("这是finaly！！！");
			System.out.println("我是Result,我的值是："+result);
		}
	}
	/*
	 * 在try-catch块外return
	 */
	public int test3()
	{
		int divider = 10;
		int result = 100;
		try{
			while(divider > -1)
			{
				divider--;
				result = result+100/divider;
			}
		}catch(Exception e)
		{
			//输出具体异常信息
			e.printStackTrace();
			System.out.println("循环抛出异常了！！！");
		}finally{ //在try-catch的返回值和被调用者之间执行finally语句块
			System.out.println("这是finaly！！！");
			System.out.println("我是Result,我的值是："+result);
		}
		System.out.println("我是test3");
		return result;
	}

}
