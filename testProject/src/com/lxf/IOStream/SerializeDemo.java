package com.lxf.IOStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 序列化与反序列话练习
 * @author lxf
 * @date 2017-02-28
 */
public class SerializeDemo {
	
	/**
	 * 将实体对象实例化后存入到指定文件file
	 * @param file
	 * @throws IOException
	 */
	public static void ObjSaveToFile(File file, Object obj) throws IOException
	{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
		oos.writeObject(obj);
		oos.flush();
	}
	/**
	 * 将实体对象实例化后存入到指定文件file的内容, 反序列化出来为对象
	 * @param file
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 */
	public static void seriFileToObj(File file) throws IOException, ClassNotFoundException
	{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
		Object  st = (Object)ois.readObject();
		ois.close();
		System.out.println(st);
	}
	

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		/*
		File file = new File("/home/lxf/test/student");
		Students st = new Students(1,"zhangsan","123456");
		SerializeDemo.ObjSaveToFile(file, st);
		SerializeDemo.seriFileToObj(file);*/
		
		File file = new File("/home/lxf/test/Foo2");
		Foo2 f = new Foo2();
		/*
		 * 将Foo2对象序列化存储到/home/lxf/test/Foo2
		 * 控制台依次输出:
		 * Foo...
			Foo1...
			Foo2...
		 */
		SerializeDemo.ObjSaveToFile(file, f);
		/*
		 * 将/home/lxf/test/Foo2中的序列化后的内容反序列化读出为对象
		 * 控制台依次输出:
		 * Foo...
			com.lxf.IOStream.Foo2@732768bb
			说明: 
			对于类对象进行反序列化操作时,如果其父类没有实现序列化接口,而子类自己实现了序列化接口,那么其父类的构造函数会被调用
			对于类对象进行反序列化操作时,如果其父类实现序列化接口,而子类没有实现了序列化接口,那么其父类的构造函数不会被调用
		 */
		SerializeDemo.seriFileToObj(file);
	}
}
class Foo {
	public Foo(){
		System.out.println("Foo...");
	}
}
class Foo1 extends Foo implements Serializable{
	public Foo1(){
		System.out.println("Foo1...");
	}
}
class Foo2 extends Foo1 implements Serializable{
	public Foo2(){
		System.out.println("Foo2...");
	}
}
