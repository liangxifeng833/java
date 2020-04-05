package lxf.filter;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/*
 * 使用WebFilter方式配置过滤器，来代替在web.xml中的过滤器配置
 * 注意 value={"/async.jsp"}，async.jsp前必须有/，否则tomcat会启动失败
 */
@WebFilter(filterName="AsyncFilter", value={"/ser/AsyncServlet"}, asyncSupported=true,dispatcherTypes={DispatcherType.REQUEST})
public class AsyncFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		System.out.println("start------------AsyncFilter");
		// TODO Auto-generated method stub
		arg2.doFilter(arg0, arg1);
		System.out.println("end------------AsyncFilter");
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}
}
