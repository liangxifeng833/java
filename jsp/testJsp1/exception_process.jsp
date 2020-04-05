<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="exception_process.jsp" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>处理excetpin.jsp中抛出的异常</title>
</head>
<body>
<h1>处理excetpin.jsp中抛出的异常</h1>
<%
	//要想处理exception.jsp中的异常，必须配置page标签中的 isErrorPage 属性未 true
%>
异常消息是：<%=exception.getMessage() %><br>
异常字符串描述：<%=exception.toString() %>

</body>
</html>