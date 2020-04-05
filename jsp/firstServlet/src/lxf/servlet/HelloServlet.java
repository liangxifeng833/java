package lxf.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//继承于HttpServlet
public class HelloServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(request, response);
		System.out.println("处理GET()请求");
		//获取浏览器输出对象
		PrintWriter out = response.getWriter();
		//用out对象给浏览器输出hello servlet
		response.setContentType("text/html;charset=utf-8");
		out.println("<strong>I am GET hello servlet</strong>");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("处理POST()请求");
		//获取浏览器输出对象
		PrintWriter out = response.getWriter();
		//用out对象给浏览器输出hello servlet
		response.setContentType("text/html;charset=utf-8");
		out.println("<strong>I am POST hello servlet</strong>");
	}
	
}
