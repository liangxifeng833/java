<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>application内置对象</title>
</head>
<body>
<h1>application内置对象</h1>
<%
	//application
	/*
		1.对象实现了用户间数据的共享，可存放全局变量, 属于整个服务器不属于某个项目
		2.开始于服务器的启动，终止于服务器的关闭
	*/
	application.setAttribute("city", "北京");
	application.setAttribute("postcode", "10000");
	application.setAttribute("email", "zhangsan@163.com");
%>
所在城市：<%= application.getAttribute("city") %><br>
邮编：<%= application.getAttribute("postcode") %><br>
邮箱：<%= application.getAttribute("email") %><br>
application中属性有：
<%
	Enumeration attr = application.getAttributeNames();
	while(attr.hasMoreElements())
	{
		out.println(attr.nextElement()+"&nbsp;&nbsp;");
	}
%><br>
jsp(SERVLET)引擎及版本号：<%= application.getServerInfo() %><br>
</body>
</html>