<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加购物车成功后跳转的页面</title>
</head>
<body>
<h1>添加购物车成功</h1>
<%
	String id = request.getParameter("id");
	String number = request.getParameter("number");
%>
<p>成功购买了编号为<%=id %>的商品<%=number %></p>
<p><a href="CartServlet?action=show">查看购物车</a></p>
</body>
</html>