<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品列表</title>
</head>
<body>
<p><a href="servlet/CartServlet?action=show">查看购物车</a></p>
<table>
	<tr>
		<th>商品名称</th>
		<th>商品图片</th>
		<th>单价</th>
		<th>操作</th>
	</tr>
	<tr>
		<td>耐克运动鞋</td>
		<td>商品图片1</td>
		<td>500</td>
		<td>
			<a href="servlet/CartServlet?id=1&action=add&number=1">添加购物车</a>
		 </td>
	</tr>
	<tr>
		<td>李宁运动鞋</td>
		<td>商品图片2</td>
		<td>200</td>
		<td>
			<a href="servlet/CartServlet?id=2&action=add&number=2">添加购物车</a>
		 </td>
	</tr>
	<tr>
		<td>阿迪运动鞋</td>
		<td>商品图片3</td>
		<td>600</td>
		<td>
			<a href="servlet/CartServlet?id=3&action=add&number=3">添加购物车</a>
		 </td>
	</tr>
</table>

</body>
</html>