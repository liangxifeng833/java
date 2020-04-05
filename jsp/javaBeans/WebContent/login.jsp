<%@ page language="java"  import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.po.Users" %><%-- 引入java Resources->src中的com.po.Users类 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统登录</title>
</head>
<body>
<h1>系统登录</h1>
<hr>
<% 
	//java Resources->src中的com.po.User类的对象Users
	Users user = new Users();
	user.setUsername("admin");
	user.setPassword("123456");
%>
<form name="myForm" action="doLogin.jsp?newPass=999999"  method="post">
用户名:<input type="text" name="username" value=""> <br>
密码:<input type="password" name="password" value=""> <br>
<input type="submit"   name="submit"  value="登录" >
</form>
</body>
</html>