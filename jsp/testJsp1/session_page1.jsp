<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*, java.text.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP内置对象session页面1</title>
</head>
<body>
<%
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	Date d = new Date(session.getCreationTime());
	session.setAttribute("username", "liangxifeng");
	session.setAttribute("password", "123456");
	session.setAttribute("hobby", "swim");
	//设置session超时时间为10秒, tomcat默认30分钟过期
	//session.setMaxInactiveInterval(10);
	//销毁session的第一种方法
	//session.invalidate();
	//另外一种是设置项目对应的web.xml 内容为:<session-config><session-timeout>1</session-timeout></session-config>
	//代表过期时间是1分钟
	//重启tomcat服务器也会是session失效
%>
session创建时间:<%= sdf.format(d) %><br>
session id: <%= session.getId() %><br>
用户名：<%= session.getAttribute("username") %><br>
session中包含属性：
<%
	String[] arr = session.getValueNames();
	for(int i=0; i<arr.length; i++)
	{
		out.println(arr[i]+"&nbsp;&nbsp;");
	}
%><br>
<a href="session_page2.jsp" target="__blank">跳转到page2页面</a>

</body>
</html>