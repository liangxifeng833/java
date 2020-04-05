package lxf.servlet;
/*
 * servlet获取web.xml中的初始化参数
 */

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetInitParamterServlet extends HttpServlet {
	//封装初始化两个参数
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(req, resp);
		resp.setContentType("text/html;charset=utf-8");
		//获取浏览器输出对象
		PrintWriter out = resp.getWriter();
		out.println("<h2>用户名："+this.getUsername());
		out.println("<h2>密码："+this.getPassword());
	}
	
	//通过init初始化函数接收web.xml中的初始化参数
	public void init(){
		this.setUsername(this.getInitParameter("username"));
		this.setPassword(this.getInitParameter("password"));
	}
	
}
