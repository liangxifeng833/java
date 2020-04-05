package lxf.listener;
/*
 * web监听器监听ServletContext对象中属性的添加，删除和修改
 * @2017-2-12
 */

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

public class MyServletContextAttributeListener implements ServletContextAttributeListener {

	@Override
	//application对象属性添加时触发
	public void attributeAdded(ServletContextAttributeEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("application对象属性添加"+arg0.getName());

	}

	@Override
	//application对象属性删除时触发
	public void attributeRemoved(ServletContextAttributeEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("application对象属性删除"+arg0.getName());

	}

	@Override
	//application对象属性修改时触发
	public void attributeReplaced(ServletContextAttributeEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("application对象属性修改"+arg0.getName());

	}

}
