package lxf.listener;
/*
 * 创建session监听器，用来统计在线人数
 * @author:liangxifeng
 * @date:2017-2-13
 */

import java.util.ArrayList;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import lxf.entity.User;
import lxf.util.SessionUtil;
@WebListener
public class MyHttpSessionListener implements HttpSessionListener {
	//定义在线人数属性
	private int userNum = 0;

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		userNum++;
		//为全局web应用都可以userNum
		arg0.getSession().getServletContext().setAttribute("userNum", userNum);
	}
	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		userNum++;
		arg0.getSession().getServletContext().setAttribute("userNum", userNum);
		ArrayList<User> userList = null;//在线用户list
		userList = (ArrayList<User>) arg0.getSession().getServletContext().getAttribute("userList");
		if(SessionUtil.getUserBySessionId(userList, arg0.getSession().getId()) != null)
		{
			userList.remove(SessionUtil.getUserBySessionId(userList, arg0.getSession().getId()));
		}
	}

}
