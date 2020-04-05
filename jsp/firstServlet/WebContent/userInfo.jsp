<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java"  import="java.util.*"   contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询用户信息</title>
</head>
<body>
<h1>>查询用户信息</h1>
<jsp:useBean id="myUser" class="lxf.entity.Users"  scope="session"></jsp:useBean>
<p>用户名：<jsp:getProperty name="myUser" property="uname" /></p>
<p>密码：<jsp:getProperty name="myUser" property="pass" /></p>
<p>生日：
<% 
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
   String birth = sdf.format(myUser.getBirthday());
    out.print(birth);
%>
</p>
<p>爱好：
<%
	String[] favs = myUser.getFavorites();
	if(favs != null)
	{
		for(String fav:favs)
		{
			out.print(fav+"&nbsp;&nbsp;");
		}	
	}

%>
</p>
<p>是否接受条款：<jsp:getProperty name="myUser" property="flat" /></p>
</body>
</html>