package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * hibernate 工具类, 初始化Hibernate, 获取会话工厂和会话对象
 * @author lxf
 *
 */

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	private static Session session;
	private static StandardServiceRegistry registry;
	
	//初始化Hibernate,  读取主配置文件Hibernate.cfg.xm, 注册必要对象
	static{
		registry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();	
	}
	
	//获取sessionFactory对象
	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
	
	//获取session对象
	public static Session getSession()
	{
	    session = sessionFactory.openSession();
		return session;
	}
	
	//关闭session
	public static void closeSession()
	{
		if(session != null)
		{
			session.clear();
		}
	}
}
