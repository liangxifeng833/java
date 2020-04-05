package lxf.listener;
/*
 * web监听器监听HttpSession对象中属性的添加，删除和修改
 * @2017-2-12
 */

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class MyHttpSessionAttributeListener implements HttpSessionAttributeListener {

	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("HttpSession对象属性添加"+arg0.getName());

	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("HttpSession对象属性删除"+arg0.getName());

	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("HttpSession对象属性修改"+arg0.getName());

	}

}
