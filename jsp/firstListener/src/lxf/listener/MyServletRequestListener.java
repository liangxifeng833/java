package lxf.listener;

/*
 * web监听器监听request对象的创建与销毁
 * @2017-2-12
 */

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class MyServletRequestListener implements ServletRequestListener {

	@Override
	//用户请求	开始时执行
	public void requestDestroyed(ServletRequestEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("requestDestroyed");

	}

	@Override
	//用户请求结束时执行
	public void requestInitialized(ServletRequestEvent arg0) {
		// TODO Auto-generated method stub
		//获取请求参数
		String name = arg0.getServletRequest().getParameter("name");
		System.out.println("requestInitialized param = "+name);
	}

}
