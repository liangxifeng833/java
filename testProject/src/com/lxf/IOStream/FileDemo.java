package com.lxf.IOStream;

import java.io.File;
import java.io.IOException;

/**
 * java.io.File类的基本API操作
 * @author lxf
 *
 */

public class FileDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("/home/lxf/test/test1.php");
		File file2 = new File("/home/lxf/test", "test2.php");
		
		//判断文件是否存在
		System.out.println(file.exists());
		/*
		 * 判断是否是目录：file.isDirectory()
		 * 判断是否是文件：file.isFile()
		 * 删除操作：file.delete();
		 * 创建单级目录：file.mkdir();
		 * 创建多级目录：file.mkdirs();
		 * 创建一个文件：file.createNewFile()
		 */
		System.out.println(file.getAbsolutePath()); ///home/lxf/test/test1.php
		System.out.println(file.getName()); //test1.php
		System.out.println(file.getParent());//home/lxf/test	
	}

}
