<%@ page language="java" import="java.util.*, java.net.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
</head>
<body>
<h1>登录页面</h1>
<hr>
<%
	String username = "";
	String password = "";
	int isCheck = 0;
	//获取cookie信息
	Cookie[] cookies = request.getCookies();
	if(cookies != null && cookies.length>0)
	{
		for(Cookie c:cookies)
		{
			if(c.getName().equals("username"))
			{
				 username = URLDecoder.decode(c.getValue(),"utf-8");
				 isCheck = 1;
			}
			if(c.getName().equals("password"))
			{
				 password = URLDecoder.decode(c.getValue(),"utf-8");
			}
		}
	}
%>
<form action="doLogin.jsp" method="post">
用户名：<input type="text" name="username" value="<%= username %>" /><br />
密码：<input type="password" name="password" value="<%= password %>" /><br />
是否保留10天内登录状态: <input type="checkbox" name="isLogin" value="is" <% if(1==isCheck) out.println("checked"); %>><br />
<input type="submit" name="submit" value="登 录" />
</form>
</body>
</html>