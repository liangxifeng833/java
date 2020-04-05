<%@ page language="java"  import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.po.Users" %><%-- 引入java Resources->src中的com.po.Users类 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>使用普通方式创建javabean的实例</title>
</head>
<body>
<h1>使用普通方式创建javabean的实例</h1>
<hr>
<% 
	//java Resources->src中的com.po.User类的对象Users
	Users user = new Users();
	user.setUsername("admin");
	user.setPassword("123456");
%>
用户名:<%= user.getUsername() %><br>
密码:<%=user.getPassword() %>
</body>
</html>