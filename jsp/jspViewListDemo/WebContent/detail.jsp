<%@ page language="java"  import="java.util.*"  contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page  import="dao.ItemsDao" %>
<%@ page  import="entity.Items" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品详情</title>
</head>
<body>

	<% 
		ItemsDao itemDao = new ItemsDao();
		Items item = itemDao.detail(Integer.parseInt(request.getParameter("id")));
		
		//获取用户cookie信息
		Cookie[] cookies = request.getCookies();
		//定义已经浏览过的商品主键字符串
		String strViewed = "";
		if(cookies!=null && cookies.length>0)
		{
			for(Cookie c:cookies)
			{
				if(c.getName().equals("viewed"))
				{
					strViewed = c.getValue();
				}
			}
			//用逗号拼接已浏览过的商品id
			strViewed+= request.getParameter("id")+",";
			//将字符分割为数组
			String[] arr = strViewed.split(",");
			if(arr!=null && arr.length>0)
			{
				//如果浏览过的商品超过10件则清空
				if(arr.length>10)
				{
					strViewed = "";
				}
			}
			Cookie cookie = new Cookie("viewed",strViewed);
			//将cookie对象保存到cookie中
			response.addCookie(cookie);
		}
	%>
<table>
	<tr>
		<td>id</td><td><%=item.getId() %></td>
	</tr>
	<tr>
		<td>商品名</td><td><%=item.getName() %></td>
	</tr>
	<tr>
		<td>价格</td><td><%=item.getPrice() %></td>
	</tr>
	<tr>
		<td>库存</td><td><%=item.getNumber() %></td>
	</tr>
	<tr>
		<td>图片</td><td><%=item.getPicture() %></td>
	</tr>
</table>

<hr>
<h2>浏览过的商品：</h2>
<% 
	ItemsDao itemDao2 = new ItemsDao();
	ArrayList<Items> itemList = itemDao2.getViewedList(strViewed);
	if(itemList!=null && itemList.size()>0)
	{
		for(Items it:itemList)
		{
%>
商品名称：<%= it.getName() %><br>
商品价格：<%= it.getPrice() %><br>
<%
		}
	}
%>

</body>
</html>