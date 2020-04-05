package com.lxf.IOStream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * 字节流练习 ( 读写又字节码文件 )
 * @author lxf
 * @date 2017-02-28
 */
public class IOByteUtil {
	/**
	 * 读取指定文件内容，按着16进制输出到控制台
	 * 并且每输出10个byte换行
	 * @param FileName
	 */
	public static void printHex(String fileName) throws IOException
	{
		//把文件作为字节流进行读操作
		FileInputStream in = new FileInputStream(fileName);
		int b;
		int i = 1;
		while( (b = in.read()) != -1 ){
			//单位数前面补0
			if( b <= 0xf)
			{
				System.out.print("0");
			}
			System.out.print(Integer.toHexString(b) + "  ");
			if( i++%10 == 0 ){
				System.out.println();
			}
		}
		in.close();
	}
	
	/*
	 * 通过一次性读取文件中内容，保存到字节数组中,并打印出16进制形式
	 */
	public static void printHexByByteArray( String fileName ) throws IOException{
		FileInputStream in = new FileInputStream(fileName);
		byte[] buf = new byte[20 * 1024];
		/*
		* 从in中批量读取字节，放到buf这个字节数组中,
		* 从第0个位置开始放，最多放buf.length个，
		* 返回的是读到的字节个数
		*/
		int bytes = in.read(buf, 0, buf.length);//一次性读完
		int j = 1;
		for ( int i = 0; i < bytes; i++) {
			if(buf[i] < 0xf){
				System.out.print("0");
			}
			System.out.print(Integer.toHexString(buf[i] & 0xff) + " ");
			if(j++%10 == 0)
			{
				System.out.println();
			}
		}
		
		/*另一种写法, 当定义的字节数组容纳不下的时候可以使用一下方式
		int bytes2 = 0;
		int m = 1;
		while((bytes2 = in.read(buf,0,buf.length)) != -1)
		{
			for(int i=0; i < bytes2; i++)
			{
				System.out.print(Integer.toHexString(buf[i] & 0xff) + " ");
				if(m++%10 == 0)
				{
					System.out.println();
				}
			}
		}*/
	}
	/**
	 * 向文件中写数据
	 * @throws IOException 
	 */
	public static void fileOutput(String fileName) throws IOException
	{
		//如果文件不存在则创建，如果存在则删除之后创建
		FileOutputStream out = new FileOutputStream(fileName);
		out.write('A');//写出了A字符的低８位
		out.write('B');//写出了A字符的低８位
		
		//write方法只能写低８位（也就是一个字节），所以写出一个整数需要写4次
		int  a = 10;
		out.write(a >>> 24); //把高８未右移到低8位置后写入文件
		out.write(a >>> 16);
		out.write(a >>> 8);
		out.write(a);
		
		//写入一个字节数组
		byte[] gdk = "中国".getBytes("utf-8");
		out.write(gdk);
		out.close();
		
		//将文件内容用16进制输出
		printHex(fileName);
	}
	

	/**
	 * 对流功能的扩展写出数据(DataOutputStream)
	 * @throws IOException 
	 */
	public static void dataOut(File file) throws IOException
	{
		DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
		//写入一个整型10
		out.writeInt(10);
		//写入一个整型20
		out.writeInt(20);
		//写入一个长整型10
		out.writeLong(10l);
		//写入一个布尔true
		out.writeBoolean(true);
		//写入一个双浮点
		out.writeDouble(10.5);
		//写入一个UTF-8编码的字符
		out.writeUTF("中国我爱你");
		out.close();
	}
	/**
	 * 对流功能的扩展读取数据(DataInputStream)
	 * 注意写入文件的内容还是字节码
	 * @throws IOException 
	 */
	public static void dataInput(File file) throws IOException
	{
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		//读取一个整形数据
		int int1 = in.readInt();
		//读取一个整形数据
		int int2 = in.readInt();
		//读取一个长整型数据
		long int3 = in.readLong();
		//读取一个boolean数据
		boolean b = in.readBoolean();
		//读取一个Double类型数据
		Double d = in.readDouble();
		//读取一个utf8编码的数据
		String str1 = in.readUTF();
		//以下数据结果：10, 20, 10, true, 10.5, 中国我爱你
		System.out.println(int1 + ", " + int2 + ", "  + int3 + ", " +  b + ", " + d + ", "  +  str1);	
		in.close();
	}
	/**
	 * copy 文件, 字节批量读取
	 * throws IOException
	 */
	public static void copyFile(File srcFile, File destFile ) throws IOException
	{
		if(!srcFile.exists())
		{
			throw  new IllegalArgumentException("文件：" + srcFile.getName() + "不存在");
		}
		if(!srcFile.isFile())
		{
			throw  new IllegalArgumentException(srcFile.getName() + "不是文件");
		}
		//返回毫秒
		long startTime = System.currentTimeMillis();
		FileInputStream in = new FileInputStream(srcFile);
		FileOutputStream out =  new FileOutputStream(destFile);
		byte[] buf  = new byte[8*1024];
		int b;
		while( (b = in.read(buf,0,buf.length)) != -1 )
		{
			out.write(buf,0,b);
			out.flush();
		}
		in.close();
		out.close();
		long endTime = System.currentTimeMillis();
		System.out.println("字节批量读取copy文件成功, 用了 " + (endTime-startTime) + "毫秒");
	}
	
	/**
	 * copy文件( 带缓冲方式 )
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 */
	public static void copyFileByBuffer(File srcFile, File destFile) throws IOException
	{
		if(!srcFile.exists())
		{
			throw  new IllegalArgumentException("文件：" + srcFile.getName() + "不存在");
		}
		if(!srcFile.isFile())
		{
			throw  new IllegalArgumentException(srcFile.getName() + "不是文件");
		}
		long startTime = System.currentTimeMillis();
		//读取文件内容
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(srcFile));
		//写出文件
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(destFile));
		int tmp = 0;
		while( (tmp = in.read()) != -1 )
		{
			//写入文件内容
			out.write(tmp);
			//刷新缓冲区
			out.flush();
		}
		in.close();
		out.close();
		long endTime = System.currentTimeMillis();
		System.out.println("带缓冲方式copy文件成功, 用了 " + (endTime-startTime) + "毫秒");
	}
	/**
	 * copy 文件 ( 单个字节方式 )
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 */
	public static void  copyFileByByte(File srcFile, File destFile ) throws IOException
	{
		if(!srcFile.exists())
		{
			throw  new IllegalArgumentException("文件：" + srcFile.getName() + "不存在");
		}
		if(!srcFile.isFile())
		{
			throw  new IllegalArgumentException(srcFile.getName() + "不是文件");
		}
		long startTime = System.currentTimeMillis();
		FileInputStream in = new FileInputStream(srcFile);
		FileOutputStream out =  new FileOutputStream(destFile);
		int tmp = 0;
		while ( (tmp = in.read()) != -1 )
		{
			out.write(tmp);
			out.flush();
		}
		in.close();
		out.close();
		long endTime = System.currentTimeMillis();
		System.out.println(" 单个字节方式copy文件成功, 用了 " + (endTime-startTime) + "毫秒");
	}
	

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File file = new File("/home/lxf/test/test.php");
		File srcFile = new File("/home/lxf/视频/20161013DK4ty.mp4");//该文件为999KB的视频
		//字节批量读取copy文件成功, 用了 14毫秒
		IOByteUtil.copyFile(srcFile, new File("/home/lxf/test/sp1.mp4"));
		//带缓冲方式copy文件成功, 用了 1409毫秒
		IOByteUtil.copyFileByBuffer(srcFile, new File("/home/lxf/test/sp2.mp4"));
		// 单个字节方式copy文件成功, 用了 1711毫秒
		IOByteUtil.copyFileByByte(srcFile, new File("/home/lxf/test/sp3.mp4"));
		/*
		//IOUtil.printHex("/home/lxf/test/test.php");
		//IOUtil.printHexByByteArray("/home/lxf/test/test.php");
		IOUtil.fileOutput("/home/lxf/test/test.php");	
		*/
		
		IOByteUtil.dataOut(file);
		IOByteUtil.dataInput(file);
		
	}

}
