package lxf.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("destroy---FirstFilter");
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
			System.out.println("start....doFilter---FirstFilter");
			HttpServletRequest req = (HttpServletRequest) arg0;
			HttpServletResponse response = (HttpServletResponse) arg1;
			//请求重定向,会有死循环产生
			//response.sendRedirect(req.getContextPath()+"/middle.jsp");
			//内部转发，不会有死循产生, 等效于在在index.jsp页面使用动作标签：<jsp:forward page="/main.jsp"></jsp:forward>
			//response.sendRedirect(req.getContextPath()+"/main.jsp");
			//req.getRequestDispatcher("main.jsp").forward(arg0, arg1);
			req.getRequestDispatcher("main.jsp").include(arg0, arg1);
			//arg2.doFilter(arg0, arg1);
			System.out.println("end....doFilter---FirstFilter");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("init--filter---FirstFilter");
	}

}
