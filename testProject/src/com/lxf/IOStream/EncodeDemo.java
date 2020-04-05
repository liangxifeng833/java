package com.lxf.IOStream;

import java.io.UnsupportedEncodingException;

/**
 * java编码练习
 * @author lxf
 */

public class EncodeDemo {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		String s = "中国AB2";
		//获取s的字节码数组,使用utf8编码
		byte[] byte1 = s.getBytes("utf-8"); 
		for (byte b : byte1) {
			/*
			 * 把字节（转换为int）以16进制的方式显示
			 * 一个字节=8位，int占32位，所以当把字节转换为int是在一个字节前面补齐24个0
			 * 0xff = 00000000 00000000 00000000 11111111
			 * &代表位运算符，按位与，0&0=0, 0&1=0,1&1=1
			 * b & 0xff 为了保留b后８位的原值
			 */
			System.out.print(Integer.toHexString(b & 0xff) + "  ");   	// e4  b8  ad  e5  9b  bd  41  42  32
			/*
			 * 以上输出如下：
			 * e4  b8  ad  e5  9b  bd  41  42  32  
			 * e4  b8  ad  ＝ 中
			 * e5  9b  bd   =  国
			 * 41 = A 
			 * 42 = B
			 * 32 = 2
			 * 以上说明：utf8编码下，一个汉子占３个字节空间，一个英文字母和数字占１个字节的空间
			 */
		}
		System.out.println("");
		
		/*
		 * 使用gbk编码方式
		 * 一个汉字占用２个字节空间，一个英文字母或数字占一个字节空间
		 */
		byte[] byte2 = s.getBytes("gbk"); 
		for (byte b : byte2) {
			System.out.print(Integer.toHexString(b & 0xff) + "  ");  //输出：d6  d0  b9  fa  41  42  32 
		}
		
		System.out.println();
		/*
		 * 使用utf-16be编码方式
		 * 一个汉字占用２个字节空间，一个英文字母或数字占２个个字节空间
		 */
		byte[] byte3 = s.getBytes("utf-16be"); 
		for (byte b : byte3) {
			System.out.print(Integer.toHexString(b & 0xff) + "  ");  //输出：4e  2d  56  fd  0  41  0  42  0  32  
		}		
		//将字节数组转换为字符串
		String str1 = new String(byte3,"utf-16be");
		System.out.println(str1); //输出：中国AB2

	}

}
