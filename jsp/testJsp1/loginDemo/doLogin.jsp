<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <%
   request.setCharacterEncoding("utf-8"); //防止中文乱码
   	String username = "";
   String password = "";
   
   username = request.getParameter("username"); //接收用户名
   password = request.getParameter("password");  //接收密码
   
   //登录成功
   if("admin".equals(username) && "admin".equals(password))
   {
	   //服务器内部转发到login_sucess.jsp页面
	   request.getRequestDispatcher("login_sucess.jsp").forward(request, response);
	   session.setAttribute("username", username);
   }else
   {
	   //请求重定向到login_failed.jsp页面
	   response.sendRedirect("login_failed.jsp");
   }
   
   %>