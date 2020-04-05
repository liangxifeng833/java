package lxf.listener;
/*
 * web监听器监听servlet上下文对象的创建与销毁
 * @2017-2-12
 */

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

	@Override
	//web服务器关闭时候执行销毁方法
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
		System.out.println("contextDestroyed");
	}

	@Override
	//web服务器启动时候执行创建初始化方法
	public void contextInitialized(ServletContextEvent arg0) {
		//获取web.xml中注册的初始化参数 ,比如用户访问http://localhost:8081/firstListener/index.jsp?name=liangxifengs
		String initParam = arg0.getServletContext().getInitParameter("initParam");
		// TODO Auto-generated method stub
		System.out.println("contextInitialized init params="+initParam);
	}
}
