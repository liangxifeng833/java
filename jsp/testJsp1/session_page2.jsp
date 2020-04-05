<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*, java.text.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP内置对象session页面2</title>
</head>
<body>
session id: <%= session.getId() %><br>
用户名：<%= session.getAttribute("username") %><br>
session中包含属性：
<%
	String[] arr = session.getValueNames();
	for(int i=0; i<arr.length; i++)
	{
		out.println(arr[i]+"&nbsp;&nbsp;");
	}
%>

</body>
</html>