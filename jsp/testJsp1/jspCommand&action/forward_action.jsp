<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试forward动作</title>
</head>
<body>
<!-- 使用jsp:forward方式将上一个login.jsp页面的请求转发到user.jsp处理 -->
<jsp:forward page="user.jsp">
<jsp:param name="username" value="我是通过jsp:param动作替换的username值" />
<jsp:param name="email" value="liangxifeng88@163.com" />
</jsp:forward>

<!-- 
	使用request.getRequestDispatcher方式将上一个login.jsp页面的请求转发到user.jsp处理
	与以上jsp:forward作用相同
-->
<% //request.getRequestDispatcher("user.jsp").forward(request, response); %>
</body>
</html>