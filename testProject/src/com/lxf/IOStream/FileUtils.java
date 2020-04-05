package com.lxf.IOStream;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 * 文件操作工具类
 * @author lxf
 *
 */

public class FileUtils {
	/**
	 * 列出指定目录下（包含子目录）的所有文件
	 * @param dir
	 * @throws IOException
	 */
	public static void listDirectory(File dir) throws IOException{
		if(!dir.isDirectory())
		{
			throw new IllegalArgumentException( dir + "不是目录");
		}
		if(!dir.exists())
		{
			throw new IllegalArgumentException( "目录：" + dir + "不是存在");
		}
		//String[] str = dir.list(); //获得子目录名称(字符串数组)
		//要想遍历子目录需要获取子目录对象
		File[] files = dir.listFiles();
		if(files!=null &&  files.length>0)
		{
			for (File file : files) {
				if(file.isDirectory())
				{
					//递归调用
					listDirectory(file);
				}else
				{
					System.out.println(file);
				}
			}
		}
	}
	/**
	 * 
	 */
	

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileUtils.listDirectory( new File("/home/lxf/test/"));
		
		File file = new File("/home/lxf/test","raf.bat");
		if(!file.exists())
			file.createNewFile();
		
		//以读写的方式实例化文件
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		//指针的位置为0
		System.out.println(raf.getFilePointer()); //0
		
		//write方法只能写一个字节（最后一个字节）
		raf.write('A');
		//System.out.println(raf.getFilePointer()); //1
		System.out.println("写入A后文件字节长度:" + raf.length());//1
		
		//写入java中最大int
		int i = 0x7fffffff;
		raf.write(i>>>24); //位右移动24为，将开头8位写进文件
		raf.write(i>>>16);//位右移动16为，将第９到第16位写进文件
		raf.write(i>>>8);  //位右移动８为，将第17到第24位写进文件
		raf.write(i);           //将第24到第32位写进文件（也就是最后８位）
		System.out.println(raf.getFilePointer()); //1
		System.out.println("调用write后文件字节长度:" + raf.length());	//1+4=5
		
		//直接写一个int
		raf.writeInt(i);
		System.out.println("调用writeInt文件字节长度:" + raf.length());//5+4=9
		
		//写入字符串
		String s = "中国";
		byte[] utf = s.getBytes("utf-8");
		raf.write(utf);
		System.out.println("写入中后文件字节长度:" + raf.length());//9+6=15
		
		//读文件必须把指针移到文件开头
		raf.seek(0);
		//一次把文件中的内容全部读到字节数组中
		byte[] buf = new byte[(int)raf.length()];
		raf.read(buf);
		//以下输出：[65, 127, -1, -1, -1, 127, -1, -1, -1, -28, -72, -83, -27, -101, -67]
		System.out.println(Arrays.toString(buf));
		
		//将读到的字节码数组转换为字符串
		String s1 = new String(buf,"utf-8");
		System.out.println(s1);
		
		//将读到的数据以16进制形式输出:41  7f  ff  ff  ff  7f  ff  ff  ff  e4  b8  ad  e5  9b  bd  
		for (byte b : buf) {
			System.out.print(Integer.toHexString(b & 0xff) + "  ");
		}
		//关闭文件
		raf.close();	
	}

}
