<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>pageContext内置对象</title>
</head>
<body>
<h1>pageContext内置对象</h1>
<%
	/*
	*	pageContext对象提供了对JSP页面内所有对象及名字空间的访问
	*   可以访问本页面所在的session, 也可以取本页面所在的application的某一属性值
	*/
%>
<%--获取session对象的信息 --%>
用户名是:<%=pageContext.getSession().getAttribute("username") %><br>
<%
	//跳转到注册页面,url地址内容不变,相当与请求转发
	//pageContext.forward("submit.jsp");

	//包含include.jsp文件
	pageContext.include("include.jsp");
%>
</body>
</html>