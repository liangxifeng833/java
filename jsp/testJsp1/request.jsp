<%@ page language="java"  import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Jsp内置对象 request测试</title>
</head>
<body>
	<%
		//request是jsp其中的一个内置对象，是 httpservletrequest类的实例，似于out
		//设置post提交过来的数据编码，get不起作用，
		//如果是get提交方式那么需要在tomcat的server.xml中设置<Connector  URIEncoding="UTF8"/>
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("pass2", "123321");
	%>
	用户名：<%= request.getParameter("userName") %><br>
	密码：<%= request.getParameter("passWord") %><br>
	爱好：
	<%
		String[] hobbys = request.getParameterValues("hobby");
		if(hobbys!=null)
		{
				//打印输出过来的数组信息
				out.println(Arrays.toString(hobbys));
				//循环输出每一个爱好
				for(int i=0; i<hobbys.length; i++)
				{
					out.println(hobbys[i]+"&nbsp;&nbsp;");
				}	
		}
	%>
   <br>自定义属性为: <%= request.getAttribute("pass2") %>
   <br>请求体的MIME类型: <%= request.getContentType() %> <%-- application/x-www-form-urlencoded   --%>
    <br>协议类型的版本号: <%= request.getProtocol() %> <%-- HTTP/1.1   --%>
    <br>服务器主机名: <%= request.getServerName() %> <%--localhost  --%>
    <br>服务器端口号: <%= request.getServerPort() %> <%--8081  --%>
    <br>请求文件的长度: <%= request.getContentLength() %> <%--82  --%>
    <br>请求客户端IP地址: <%= request.getRemoteAddr() %> 
    <br>请求的文件在服务器的物理路径: <%= request.getRealPath("request.jsp") %><%-- /usr/local/tomcat/webapps/webFirst/request.jsp  --%>
    <br>请求的上下文路径: <%= request.getContextPath() %><%--  /webFirst	  --%>
    
</body>
</html>