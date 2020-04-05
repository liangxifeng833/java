<%@ page language="java"  import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.po.Users" %><%-- 引入java Resources->src中的com.po.Users类 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>javaBean的四个作用域范围</title>
</head>
<body>
<h1>javaBean的四个作用域范围</h1>
<hr>

<jsp:useBean id="myUsers" class="com.po.Users"  scope="page"></jsp:useBean>

<!--  使用动作标签获取用户名密码 -->
<%--
用户名：<jsp:getProperty property="username" name="myUsers"/><br>
密码：<jsp:getProperty property="password" name="myUsers"/><br>
- --%>
<br>

<!--  scope=application作用域　使用内置对象获取用户名和密码 -->
<%--
用户名：<%= ((Users)application.getAttribute("myUsers")).getUsername() %><br>
密码：<%= ((Users)application.getAttribute("myUsers")).getPassword() %><br>
- --%>
<br>

<!--  scope=session作用域　使用内置对象获取用户名和密码 -->
<%--
用户名：<%= ((Users)session.getAttribute("myUsers")).getUsername() %><br>
密码：<%= ((Users)session.getAttribute("myUsers")).getPassword() %><br>
- --%>

<!--  scope=request作用域　使用内置对象获取用户名和密码 -->
用户名：<%= ((Users)request.getAttribute("myUsers")).getUsername() %><br>
密码：<%= ((Users)request.getAttribute("myUsers")).getPassword() %><br>

<br>
</body>
</html>