<%@ page language="java"  import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>使用userBean动作方式创建javabean的实例</title>
</head>
<body>
<h1>使用userBean动作方式创建javabean的实例</h1>
<hr>
<%--java Resources->src中的com.po.User类的对象Users --%>
java Resources->src中的com.po.User类的对象Users
<jsp:useBean id="myUsers" class="com.po.Users"  scope="page"></jsp:useBean>
<%
	myUsers.setPassword("000000");
 	myUsers.setUsername("zhangsan");
%>
用户名:<%= myUsers.getUsername() %><br>
密码:<%=myUsers.getPassword() %>
</body>
</html>