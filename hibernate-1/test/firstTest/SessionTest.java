package firstTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Before;
import org.junit.Test;

import entity.Grade;
import entity.Student2;
import test.oneToMangTest;
import util.HibernateUtil;

/**
 * 测试使用openSession和getCurrentSession两种方式获取session对象的区别
 * openSession每次创建新的session对象
 * getCurrentSession使用现有的session对象（单例模式）
 * @author lxf
 *
 */

public class SessionTest {

	
	/**
	 *  使用openSession方式创建session对象
	 */
	@Test
	public void testOpenSession()
	{
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		//创建会话工厂对象
		SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();	   
	     //创建会话对象
	     Session session = sessionFactory.openSession();	
	     Session session2 = sessionFactory.openSession();	
	     //openSession每次创建新的session对象
	     System.out.println(session==session2); //false
	     if(session != null)
	     {
	    	 System.out.println("openSession方式创建session成功");
	     }else
	     {
	    	 System.out.println("openSession方式创建session失败");
	     }
	}
	/**
	 *  使用getCurrentSession方式创建session对象
	 *  需要额外在hibernate.cfg.xml中配置
	 */
	@Test
	public void testGetCurrentSession()
	{
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		//创建会话工厂对象
		SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();	   
	     //创建会话对象
	     Session session = sessionFactory.getCurrentSession();
	     Session session2 = sessionFactory.getCurrentSession();
	     // getCurrentSession使用现有的session对象（单例模式）
	     System.out.println(session==session2);//true
	     if(session != null)
	     {
	    	 System.out.println("getCurrentSession方式创建session成功");
	     }else
	     {
	    	 System.out.println("getCurrentSession方式创建session失败");
	     }
	}
	
	public void add()
	{
	       Grade grade = new Grade("高一三班", "高一三班都是精英");
	        Student2 st1 = new Student2("张三", "男");
	        Student2 st2 = new Student2("李四", "女");
	        Set<Student2> student2s = new HashSet<Student2>();
	        student2s.add(st1);
	        Student2[] sts = {st1, st2};
	        //将两个学生对象添加到set中
	        student2s.addAll(Arrays.asList(sts));
	        grade.setStudent2(student2s);
	        System.out.println(grade);
	        /*
	        //保存到数据库中
	        Session session  = HibernateUtil.getSession();
	        //开启事务
	        Transaction tx = session.beginTransaction();
	        session.save(grade);
	        session.save(st1);
	        session.save(st2);
	        //提交事务
	        tx.commit();
	        HibernateUtil.closeSession();*/
	}
	
}
