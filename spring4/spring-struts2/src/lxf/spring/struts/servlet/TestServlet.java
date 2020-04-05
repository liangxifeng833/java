package lxf.spring.struts.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import lxf.spring.struts.beans.Person;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// １．从application对象中得到IOC容器的引用
	    ServletContext servletContext = getServletContext();
	    ApplicationContext ctx = (ApplicationContext)servletContext.getAttribute("ApplicationContext");
	    
	    //２．从IOC容器中得到需要的bean
	    Person person = (Person) ctx.getBean("person");
	    person.hello();
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
}
