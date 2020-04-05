package lxf.spring.struts.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringServletContextListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public SpringServletContextListener() {

    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
        
    }
    
    /**
     * web容器创建时触发，也就是ServletContext对象创建的时候触发
     */
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        //1．web.xml<context-param>中获取Spring配置文件名
        ServletContext servletContext = arg0.getServletContext();
        String configName = servletContext.getInitParameter("configLocation");
        
        //2．创建IOC容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configName);
        
        //3．把IOC容器放到ServletContext一个属性中
        servletContext.setAttribute("ApplicationContext", ctx);
        
    }
	
}
