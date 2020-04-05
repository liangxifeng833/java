package servlet;
/*
 * servlet处理请求
 * @author: liangxifeng
 * @date:2017-02-16
 * 
 * 页面访问：http://localhost:8081/jspServletTable/ShowReport　
 * 会调用service.getProfit();　将结果存放到上下文对象ServletContext属性中
 * 然后跳转到jsp页面，在jsp页面获取ServletContext即application的数据并显示
 */

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Service;

/**
 * Servlet implementation class ShowReport
 */
@WebServlet("/ShowReport")
public class ShowReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowReport() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		Service service = new Service();
		List list;
		list = service.getProfit();
		request.getServletContext().setAttribute("profits", list);
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
