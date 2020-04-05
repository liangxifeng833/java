<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="lxf.entity.*, java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购物车显示</title>
</head>
<body>
<h1>购物车列表</h1>
<%
	if(request.getSession().getAttribute("cart") != null)
	{
		//从购物车中获取数据
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		HashMap<Items,Integer> goods = cart.getGoods();
		//获取商品key的集合，因为购物车中goods存储商品的泛型是<商品对象，购买数量>
		Set<Items> item = goods.keySet();
		//通过迭代器遍历购物车中商品信息
		Iterator<Items> it = item.iterator();
		while(it.hasNext()){
			Items i = it.next();
			%>
				<p>
					商品名称：<%=i.getGoods_name() %>
					<img src="<%=i.getImg_path()%>">
					商品单价：<%=i.getPrice() %>
					购买数量：<%=goods.get(i) %>
				</p>
				<p>商品价格：<%=i.getPrice()*goods.get(i) %></p>
				<p><a href="CartServlet?id=<%=i.getId()%>&action=delete">删除购物车</a></p>
			<%	
		}
		%>
		<p>商品总价：<%=cart.getTotalPrice()%></p>
		<%
	}
%>
</body>
</html>