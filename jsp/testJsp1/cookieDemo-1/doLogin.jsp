<%@ page language="java"  import="java.util.*, java.net.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>处理登录页面</title>
</head>
<body>
<h1>处理登录页面</h1>
<hr>
<%
	request.setCharacterEncoding("utf-8");
	String username = "";
	String password = "";
	//使用URLEncoder解决cookie中保存中文字符串问题
	username = URLEncoder.encode(request.getParameter("username"),"utf-8");
	password = URLEncoder.encode(request.getParameter("password"),"utf-8");
	String[] isLogin = request.getParameterValues("isLogin");
	int sign = 0;
	//是否选择保留10天的登录状态
	if(isLogin != null && isLogin.length>0)
	{
		//初始化cookie用户名对象
		Cookie uname = new Cookie("username",username);
		uname.setMaxAge(864000);//设置cookie有效期为10天
		//初始化cookie密码对象
		Cookie pass = new Cookie("password",password);
		pass.setMaxAge(864000);
		
		//将用户名和密码对象注册到客户端的cookie中
		response.addCookie(uname);
		response.addCookie(pass);	
		sign = 1;
	}else
	{
		//获取cookie信息
		Cookie[] cookies = request.getCookies();
		for(Cookie c:cookies)
		{
			//设置立即过期
			c.setMaxAge(0);
			response.addCookie(c);
		}
	}
%>
<% 
	if(1==sign)
	{
		out.println(" 登录成功<br>");
	}else
	{
		out.println(" 登录失败<br>");
	}
%>
<a href="user.jsp">查看用户信息</a>
</body>
</html>