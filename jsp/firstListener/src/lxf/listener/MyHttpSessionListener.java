package lxf.listener;
/*
 * web监听器监听session对象的创建与销毁
 * @2017-2-12
 */

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyHttpSessionListener implements HttpSessionListener {

	@Override
	//当用户访问本项目第一个页面时候触发
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("sessionCreated");
	}
	@Override
	//session销毁时触发该方法, session到期（过期可以在web.xml中配置），关闭浏览器，手动销毁session等
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("sessionDestroyed");
	}

}
