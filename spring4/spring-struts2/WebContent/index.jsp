<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="lxf.spring.struts.beans.Person" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
   //１．从application域对象中得到IOC容器的实例
   ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
  //２．从IOC容器中得到Bean
  Person p = (Person)ctx.getBean("person");
  //３．使用Bean
  p.hello();  
%>
<a href="person-save">struts2测试跳转到对应的person-save的action</a>
</body>
</html>