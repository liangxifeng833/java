package lxf.spring.hibernate.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lxf.spring.hibernate.dao.BookShopDao;
import lxf.spring.hibernate.service.BookShopService;
/**
 * 单元测试Spring整合hibernate类
 * @author lxf
 */
public class SpringHibernateTest {
    private ApplicationContext ctx = null;
    private BookShopService bookShopService = null;
    private BookShopDao bookShopDao;
    {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        bookShopService = (BookShopService)ctx.getBean("bookShopService");
        bookShopDao = (BookShopDao)ctx.getBean("bookShopDao");
    }
    /**
     * 测试买书事务
     */
    @Test
    public void testBuyBook()
    {
        bookShopService.buyBook(1, 1);
    }

    /**
     * 测试数据源连接
     * @throws SQLException 
     */
    @Test
    public void testDataSource() throws SQLException {
        DataSource dataSource = (DataSource)ctx.getBean("dataSource");
        System.out.println(dataSource.getConnection());
        
    }

}
