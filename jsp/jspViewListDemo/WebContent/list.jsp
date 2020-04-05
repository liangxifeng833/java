<%@ page language="java"  import="java.util.*"  contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page  import="dao.ItemsDao" %>
<%@ page  import="entity.Items" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品列表页</title>
</head>
<body>
<table>
	<tr>
		<th>id</th>
		<th>商品名称</th>
		<th>单价</th>
		<th>库存</th>
		<th>图片</th>
		<th>操作</th>
	</tr>
	<% 
		ItemsDao itemDao = new ItemsDao();
		ArrayList<Items> itemArr = itemDao.getAll();
		if(itemArr!=null && itemArr.size()>0)
		{
			for(Items item:itemArr)
			{
	%>
	<tr>
		<td><%=item.getId() %></td>
		<td><%=item.getName() %></td>
		<td><%=item.getPrice() %></td>
		<td><%=item.getNumber() %></td>
		<td><%=item.getPicture() %></td>
		<td><a href="detail.jsp?id=<%=item.getId() %>">详情</a></td>
	</tr>
	<%
			}
		}
	%>
</table>

</body>
</html>