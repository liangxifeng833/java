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
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {
	//声明过滤器配置属性, 在初始化函数中为其赋值,用来保存web.xml中设定的初始化参数
	private FilterConfig config;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		
		//读取web.xml中配置的初始化字符集参数
		String charset = config.getInitParameter("charset");
		charset = charset==null ? "UTF-8" : charset;
		//设置字符编码
		request.setCharacterEncoding(charset);
		
		//读取web.xml中配置的初始化参数
		String noLoginPaths = config.getInitParameter("noLoginPaths");
		if(noLoginPaths != null){
			//分割web.xml中的<param-value>login.jsp;success.jsp;fail.jsp</param-value>为数组
			String[] strArr = noLoginPaths.split(";");
			//循环判断如果是用户直接访问的是登录，成功，失败三个页面那么直接放行，不进行登录验证,
			for(int i=0; i<strArr.length; i++){
				if(strArr[i]==null || "".equals(strArr[i])) continue;
				if(request.getRequestURI().indexOf(strArr[i])!=-1){
					arg2.doFilter(arg0, arg1);
					return;
				}
				
			}
		}
		HttpSession session = request.getSession();
		if(session.getAttribute("username") != null){
			//如果session中存下username则放行
			arg2.doFilter(arg0, arg1);
		}else{
			//否则跳转到登录页面登录
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		config = arg0;
	}

}
