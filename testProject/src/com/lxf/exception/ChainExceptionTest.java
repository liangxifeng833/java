package com.lxf.exception;
/*
 * java异常链的测试
 */

public class ChainExceptionTest {
	
	/*
	 * test1()抛出“喝大了”的异常
	 * test2()调用test1()，捕获“喝大了”异常，并且包装成运行时异常，继续抛出
	 * main()方法中，调用test2()方法，尝试捕获test2()方法抛出的异常
	 */
	public void test1() throws DrunkException
	{
		throw new DrunkException("喝车别开酒");
	}
	public void test2()
	{
		try{
			test1();
		} catch (DrunkException e) {
			//e.printStackTrace();
			//实例运行时异常对象
			RuntimeException newExc = new RuntimeException("司机一滴酒，亲人两行泪～～");
			//引用原始异常，从而实现异常链的功能
			newExc.initCause(e);
			throw newExc;
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChainExceptionTest ct = new ChainExceptionTest();
		try{
			ct.test2();
		}catch(Exception e){
			e.printStackTrace();			
		}

	}

}
