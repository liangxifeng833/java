<%@ page language="java"  import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.po.Users" %><%-- 引入java Resources->src中的com.po.Users类 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>setProperty动作元素</title>
</head>
<body>
<h1>setProperty动作元素</h1>
<hr>
<%-- 实例化users对象 --%>
<jsp:useBean id="myUsers" class="com.po.Users"  scope="request"></jsp:useBean>
<!-- 根据表单自动匹配所有属性(*), 表单的name和Users属性名匹配 -->
<jsp:setProperty property="*" name="myUsers"/>

<!-- 根据表单自动匹配部分属性, property的值就是对象myUsers的属性值 -->
<jsp:setProperty property="username" name="myUsers"/>

<!-- 与表单无关，通过手工设置赋值给myUsers的属性 -->
<jsp:setProperty property="password" name="myUsers"  value="123456"/>

<!-- 与表单无关，　根据URL地址的参数给属性赋值 -->
<jsp:setProperty property="password" name="myUsers"   param="newPass"/>

用户名:<%= myUsers.getUsername() %><br>
使用动作标签获取用户名：<jsp:getProperty property="username" name="myUsers"/><br />
密码:<%=myUsers.getPassword() %><br>
使用动作标签获取密码：<jsp:getProperty property="password" name="myUsers"/><br />

<a href="testScope.jsp">测试javaBean的四个作用域范围</a>
<%
	//如果scope＝request，那么页面与页面之间需要请求转发方式跳转，否则咋testScope.jsp页面中request.getAttribute属性无法接收到值
	request.getRequestDispatcher("testScope.jsp").forward(request, response);
%>
</body>
</html>