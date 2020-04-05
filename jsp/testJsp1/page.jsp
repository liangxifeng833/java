<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>page内置对象</title>
</head>
<body>
<h1>page内置对象</h1>
<%
	/*
	* page对象就是只想当前JSP页面本身，有点像类中的this指针，是java.lang.Object类的实例
	* page对象有object类的所有方法
	*/
%>
当前page页面对象的字符串描述：<%= page.toString() %> <%-- 输出结果为：org.apache.jsp.page_jsp@8b6500 --%>
</body>
</html>