<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试include动作</title>
</head>
<body>
<h1>测试include动作</h1>
<hr>
<%-- 
	include动作 最终在/usr/local/tomcat-7.0.32/work/Catalina/localhost/webFirst/org/apache/jsp/
	服务器目录中生成date_jsp.java 和 include_action.java两个servlet类
	include动作包含的是date.jsp的输出结果，编译完后包含的是date_jsp.class的输出结果
 --%>
<jsp:include page="date.jsp" flush="false"></jsp:include>
</body>
</html>