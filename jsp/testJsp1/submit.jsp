<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>表单提交测试</title>
</head>
<body>
<form action="response.jsp" method="post">
<table>
	<tr>
		<td>用户名：</td>
		<td><input name="userName" type="text" value=""></td>
	</tr>
	<tr>
		<td>密码：</td>
		<td><input name="passWord" type="password"  value=""></td>
	</tr>
	<tr>
		<td>爱好：</td>
		<td>
			篮球<input   name="hobby" type="checkbox"  value="basketball">
			游泳<inpu   name="hobby" type="checkbox"  value="swim">
			跑步<input  name="hobby" type="checkbox"  value="paobu">
			羽毛球<input  name="hobby" type="checkbox"  value="yumaoqiu">
		</td>
	</tr>
	<tr><td colspan="1"><input type="submit" name="提交"></td></tr>
	<a href="http://localhost:8081/webFirst/request.jsp?userName=张三&passWord=123">get方式提交表单</a>
</table>
</form>
</body>
</html>