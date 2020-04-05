package lxf.listener;
/*
 *ervlet3.0下使用webListener注解代替web.xml注册，使用监听器
 */
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener("This is My Servlet 3.0 Lisener")
public class Servlet3WebListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("servlet3.0下使用webListener注解代替web.xml－－－contextDestroyed");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("servlet3.0下使用webListener注解代替web.xml－－－contextInitialized");

	}
}
