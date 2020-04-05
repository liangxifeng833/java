<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="exception_process.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试异常的页面</title>
</head>
<body>
<h1>测试异常的页面</h1>
<%
	//要想抛出以下异常则必须配置page标签的errorPage="exception_process.jsp" 让哪个页面处理这个异常
	System.out.println(100/0);//抛出运行时异常，也就是算数异常,因为除数是0
%>

</body>
</html>