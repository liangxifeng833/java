<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="lxf.entity.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>当前在线人数</title>
</head>
<body>
<p>当前在线人数为${userNum}</p>
<%
	ArrayList<User> userList =  (ArrayList<User>)request.getServletContext().getAttribute("userList");
	for(User user: userList){
%>
	<strong>第一次访问时间：</strong><%= user.getFirstTimeString() %><br />
	IP地址：<%= user.getIpString() %><br />
	SessionID：<%= user.getSessionIdString() %><br /><br />
<%
	}
%> 
</body>
</html>