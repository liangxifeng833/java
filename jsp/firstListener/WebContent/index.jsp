<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>this is first listener page</title>
</head>
<body>
<p>this is first listener page</p>
<%=request.getSession().getAttribute("userObj") %>
<%=request.getSession().getAttribute("sessionName") %>
<button onclick="location.href='<%=request.getContextPath() %>/init.jsp';">属性赋值</button>
<button onclick="location.href='<%=request.getContextPath() %>/destroy.jsp';">属性删除</button>
</body>
</html>