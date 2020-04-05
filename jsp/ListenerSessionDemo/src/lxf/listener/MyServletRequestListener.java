package lxf.listener;
/*
 * 创建ServletRequest监听器，用来记录在线访问人员信息
 * @author:liangxifeng
 * @date:2017-2-13
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

import lxf.entity.User;
import lxf.util.SessionUtil;

@WebListener
public class MyServletRequestListener implements ServletRequestListener {
	//定义存储在线用户列表
	private ArrayList<User> userList;

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest)arg0.getServletRequest();
		String sessionIdString = request.getSession().getId();
		
		userList = (ArrayList<User>) arg0.getServletContext().getAttribute("userList");
		if(userList == null){
			userList = new ArrayList<User>();
		}
		//如果是新用户则新增到在线用户列表
		if(SessionUtil.getUserBySessionId(userList,sessionIdString) == null){
			User user = new User();
			user.setIpString(request.getRemoteAddr());
			user.setSessionIdString(sessionIdString);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			user.setFirstTimeString(sdf.format(new Date()));
			userList.add(user);
		}
		arg0.getServletContext().setAttribute("userList", userList);
	}



}
