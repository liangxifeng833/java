<%@page import="java.text.*, java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//pageContext.jsp中包含该页面
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 hh时mm分ss秒");
	String nowDate = sdf.format(date);
	out.println(nowDate+"<br>");
%>
