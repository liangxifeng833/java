<%@page import="lxf.entity.User"%>
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
	request.getSession().setAttribute("userObj", new lxf.entity.User());
%>
<p>属性初始化页面</p>
<button onclick="location.href='<%=request.getContextPath() %>/init.jsp';">属性赋值</button>
<button onclick="location.href='<%=request.getContextPath() %>/destroy.jsp';">属性删除</button>
</body>
</html>