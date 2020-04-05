package firstTest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import javax.print.attribute.standard.MediaPrintableArea;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entity.Address;
import entity.Students;

/**
 * 测试类
 * @author lxf
 *
 */

public class StudentsTest {
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	@Before
	//初始化配置
	public void init()
	{
		/*
		 * 注意：我的hibernate是5.0和慕课网上讲的Hibernate4.2.4（就是4.0）两个版本的配置方法不一样
		 */		
		//创建服务注册对象
		//如果.cfg.xml取了其他的名字，要改为.configure("名字.cfg.xml")
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		//创建会话工厂对象
	     sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();	     
	     //创建会话对象
	     session = sessionFactory.openSession();
	    //开启事务
	    transaction = session.beginTransaction();		
	}
	
	@Test
	//真实的单元测试
	public void testSaveStudent()
	{
		//Students st = new Students(1,"张三丰", "nan", new Date(), "wds");
		Students st = new Students();
		Address address = new Address("68353","13301223034","1212");
		st.setAddress(address);
		st.setBirthday(new Date());
		st.setGender("女");
		st.setSname("小美");
		/*
		 * hibernate默认对数据库的操作默认是非自动提交的，以下是手动开启的方式（不推荐）
		 */
		/*
		session.doWork(new Work(){
			@Override
			public void execute(Connection connection) throws SQLException {
				// TODO Auto-generated method stub
				//开启关系型数据库的自动提交
				connection.setAutoCommit(true);
			}	
		});
		*/
		//保存对象进入数据库
		session.save(st); 	
//		session.flush(); 手动开启自动提交必须调用flush
	}
	
	/*
	 * 写入二进制数据到数据库
	 */
	@Test
	public void testWriteBlob() throws IOException{
		Students st = new Students(1,"张三丰", "nan", new Date());
		//先获得照片文件
		File f = new File("/home/lxf/图片/ljlj.jpg");
		System.out.println(f.toString());
		//获得该文件的输入流
		InputStream input = new FileInputStream(f);
		//创建一个Blob对象
		Blob image = Hibernate.getLobCreator(session).createBlob(input, input.available());
		st.setPicture(image);
		//保存学生信息
		session.save(st);
	}
	
	/*
	 * 从数据库中读取二进制对象（图片）到物理磁盘
	 */
	@Test
	public void testReadBlob() throws SQLException, IOException
	{
		Students s = (Students)session.get(Students.class, 1);
		//获取图片的二进制 (Blob对象)
		Blob image = s.getPicture();
		//获得输入流
		InputStream input = image.getBinaryStream();
		//创建输出流
		File f = new File("/home/lxf/图片/ljlj-new.jpg");
		//获得输出流
		OutputStream output = new FileOutputStream(f);
		//创建缓冲区
		byte[] buff = new byte[input.available()];
		input.read(buff);
		output.write(buff);
		input.close();
		output.close();
	}
	
	/*
	 * get方式查询学生
	 * get方式会立刻发送sql语句去查询数据库返回持久化对象
	 * 查询数据库中不存在的数据库时，get方法返回null
	 */
	@Test
	public void testGetStudent()
	{
		//查询id=1的数据库记录
		Students st = session.get(Students.class, 1);
		//System.out.println(st);
		System.out.println(st.getClass().getName());//输出：entity.Students
	}
	/*
	 * Load方式查询学生
	 * load方法会在调用后返回一个代理对象，该代理对象只保存实体对象的id，直到使用对象的非主键属性时菜会发出sql语句查询数据库
	 * load方法查询不到数据时会抛出异常：org.hibernate.ObjectNotFoundException
	 */
	@Test
	public void testLoadStudent()
	{
		//查询id=1的数据库记录
		Students st = session.load(Students.class, 1);
		//System.out.println(st);
		System.out.println(st.getClass().getName());//输出：entity.Students_$$_jvstf0c_0
	}
	/*
	 * 修改学生
	 */
	@Test
	public void testUpdateStudent()
	{
		//查询id=1的数据库记录
		Students st = session.load(Students.class, 1);
		st.setGender("男");
		session.update(st);
	}
	/*
	 * 删除学生
	 */
	@Test
	public void testDelStudent()
	{
		//查询id=1的数据库记录
		Students st = session.load(Students.class, 1);
		session.delete(st);
	}
	
	@After
	//释放资源
	public void destroy()
	{
		//提交事务
		transaction.commit();
		//关闭会话
		session.close();
		//关闭会话工厂
		sessionFactory.close();	
	}
}
