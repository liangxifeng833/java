<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/struts2-test1/myRoute/login.action" method="post">
用户名：<input type="text" name="username"><s:fielderror name="username"></s:fielderror><br>
密码：<input type="password" name="password"> <br>
图书1：<input type="text" name="bookList[0]"> <br>
图书2：<input type="text" name="bookList[1]"> <br>
用户1：<input type="text" name="userList[0].username"> <br>
用户2：<input type="text" name="userList[1].username"> <br>
年龄：<input type="text" name="age"> <br>
<input type="submit" name="submit" value="提交" /><br>
</form>
</body>
</html>