package com.lxf.IOStream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * 字符流练习 (读写普通文件)
 * @author lxf
 *
 */
public class IOCharUtil {
	
	/**
	 * copy文本文件
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 */
	public static void copyFile(File srcFile, File destFile) throws IOException
	{
		//原文件读取
		FileInputStream in = new FileInputStream(srcFile);
		InputStreamReader isr = new InputStreamReader(in, "utf-8");
		//目标文件写入
		FileOutputStream out = new FileOutputStream(destFile);
		OutputStreamWriter osw = new OutputStreamWriter(out,"utf-8");
		
		//read() 返回的是读取到字符的个数
		/*
		 //一个字符的读取方式
		int c;
		while ( (c = isr.read()) != -1 )
		{
			System.out.print((char)c);
		}*/
		long startTime = System.currentTimeMillis();
		//先读取字符到字符数组中, 然后将字符数组转换为字符串
		char[] buffer = new char[8*1024];
		int tmp;
		/*
		 * 批量读取,放入到buffer这个字符数组, 从第0个位置开始放置,最多放buffer.length个
		 * isr.read(buffer, 0, buffer.length)返回的是读到字符的个数
		 */
		while( (tmp = isr.read(buffer, 0, buffer.length)) != -1)
		{
			String s = new String(buffer,0,tmp);
			System.out.println(s);
			osw.write(buffer,0,tmp);
			osw.flush();
		}
		in.close();
		isr.close();
		out.close();
		osw.close();
		long endTime = System.currentTimeMillis();
		System.out.println("文件:" + srcFile.getName() + "copy成功, 程序执行时间为: " + (endTime-startTime));
	}
	
	/**
	 * 利用FileReader和FildeWriter对文本文件进行copy
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 */
	public static void copyFilebyFileReader(File srcFile, File destFile) throws IOException
	{
		FileReader  reader = new FileReader(srcFile);
		FileWriter writer = new FileWriter(destFile);
		char[] chars = new char[8*1024];
		int c;
		//reader.read(chars, 0, chars.length)返回读取到的字符个数
		while( (c = reader.read(chars, 0, chars.length)) != -1)
		{
			writer.write(chars, 0, c);
		}
		reader.close();
		writer.close();
	}
	
	/**
	 * copy文件 (使用BufferReader和BufferWriter方式)
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 */
	public static void copyFileByBufferReader(File srcFile, File destFile) throws IOException
	{
		//对文件进行读取操作
		FileInputStream in = new FileInputStream(srcFile);
		InputStreamReader isr = new InputStreamReader(in, "utf-8");
		//FileReader  isr = new FileReader(srcFile);
		BufferedReader br = new BufferedReader(isr);
		
		//对文件进行写入操作
		FileOutputStream out = new FileOutputStream(destFile);
		OutputStreamWriter isr1 = new OutputStreamWriter(out, "utf-8");
		BufferedWriter bw = new BufferedWriter(isr1);
		
		String line;
		//每次读一行
		while( (line = br.readLine()) != null )
		{
			bw.write(line);
			//换行符
			bw.newLine();
			bw.flush();
		}
		isr.close();
		bw.close();
	}
	
	/**
	 * copy文件 (  使用BufferReader和PrintWriter方式)
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 */
	public static void copyFileByBufferReadPrintWriter(File srcFile, File destFile) throws IOException
	{
		//对文件进行读取操作
		FileInputStream in = new FileInputStream(srcFile);
		FileReader  isr = new FileReader(srcFile);
		BufferedReader br = new BufferedReader(isr);
		
		PrintWriter pw = new PrintWriter(destFile);
		String line;
		while( (line = br.readLine()) != null )
		{
			System.out.println(line);
			pw.println(line);
			pw.flush();
		}
		br.close();
		pw.close();
		
	}
	

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File srcFile = new File("/home/lxf/test/test.php");
		File destFile = new File("/home/lxf/test/test-5.php");
		//IOCharUtil.copyFile(srcFile, destFile);
		//IOCharUtil.copyFilebyFileReader(srcFile, destFile);
		//IOCharUtil.copyFileByBufferReader(srcFile, destFile);
		IOCharUtil.copyFileByBufferReadPrintWriter(srcFile, destFile);
	}

}
