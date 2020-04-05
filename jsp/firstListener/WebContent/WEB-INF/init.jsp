<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>属性初始化页面</title>
</head>
<body>
<%
	request.setAttribute("requestName", "requestValue");
	request.getSession().setAttribute("sessionName", "sessionValue");
	request.getServletContext().setAttribute("contextName", "contextValue");
%>
<p>属性初始化页面</p>
</body>
</html>