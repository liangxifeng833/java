<%@ page language="java"  import="java.util.*, beans.*"  contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>利润表</h1>
<table>
	<tr>
		<th>序号</th>
		<th>商品名称</th>
		<th>卖出数量</th>
		<th>交易笔数</th>
		<th>盈利额</th>
	</tr>
<%
	ArrayList<Profit> list = null;
	
	if( application.getAttribute("profits") != null )
	{
		list = (ArrayList) application.getAttribute("profits");
		if( list.size() > 0 )
		{
			int i=0;
			for(Profit pf :  list)
			{
				i++;
%>	
	<tr>
		<td><%=i%></td>
		<td><%=pf.getGoodsName() %></td>
		<td><%=pf.getNumber() %></td>
		<td><%=pf.getTimes() %></td>
		<td><%=pf.getProfit() %></td>
	</tr>		
<%
			}
		}
	}
%>
</table>
</body>
</html>