<%@ page language="java" import="java.util.*,java.net.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看cookie中用户信息</title>
</head>
<body>
<h1>查看cookie中用户信息</h1>
<hr>
<%
	String username = "";
	String password = "";
	//通过上个forward_action页面的<jsp:param>动作为属性赋值得到的,在login.jsp页面并没有email表单
	String email = "";
	request.setCharacterEncoding("utf-8");
	if(request.getParameter("username") != null)
	{
		username = request.getParameter("username") ;
	}
	if(request.getParameter("password") != null)
	{
		password = request.getParameter("password");
	}
	if(request.getParameter("email") != null)
	{
		email = request.getParameter("email");
	}
%>
用户名：<%=username %><br />
密码：<%=password %><br />
邮箱：<%=email %><br />
</body>
</html>