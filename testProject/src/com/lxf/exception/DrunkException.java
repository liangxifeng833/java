package com.lxf.exception;
/*
 * 自定义异一个喝大了的异常,自定义异常的父类可以是Exception，也可以是其他异常类
 */

public class DrunkException extends Exception{
	
	//定义无参构造器
	public DrunkException()
	{
		
	}

	//方法重载定义有参构造器
	public DrunkException(String message)
	{
		super(message);
	}
}
