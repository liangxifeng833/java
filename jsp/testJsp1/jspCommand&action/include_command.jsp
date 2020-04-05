<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试include指令</title>
</head>
<body>
<h1>测试include指令</h1>
<hr>
<%-- 
	include指令 最终在/usr/local/tomcat-7.0.32/work/Catalina/localhost/webFirst/org/apache/jsp/
	服务器目录中生成include_command.java一个servlet类
	include指令包含的是date.jsp的源代码到include_command.java类中
	
	页面经常变化使用jsp:include动作
	页面不经常变化使用 include指令
	jsp:include动作在请求期间运行，而include指令在编译期间运行
 --%>
<%@ include file="date.jsp" %>
</body>
</html>