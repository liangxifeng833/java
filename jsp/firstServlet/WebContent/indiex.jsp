<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>第一个servlet demo</title>
</head>
<body>
<h1>第一个servlet小例子</h1>
<hr>
<a href="servlet/HelloServlet">Get请求HelloServlet</a>
<form action="servlet/HelloServlet" method="post">
	<input type="submit" name="Post请求HelloServlet" >
</form>
<br>
<a href="servlet/GetInitParamterServlet">跳转通过web.xml中配置的初始化参数，servlet获取该初始化参数</a>

</body>
</html>