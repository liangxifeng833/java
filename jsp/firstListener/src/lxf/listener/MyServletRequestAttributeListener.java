package lxf.listener;
/*
 * web监听器监听ServletRequest对象中属性的添加，删除和修改
 * @2017-2-12
 */
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;

public class MyServletRequestAttributeListener implements ServletRequestAttributeListener {

	@Override
	public void attributeAdded(ServletRequestAttributeEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("ServletRequest对象属性添加"+arg0.getName());

	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("ServletRequest对象属性删除"+arg0.getName());

	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("ServletRequest对象属性修改"+arg0.getName());

	}

}
