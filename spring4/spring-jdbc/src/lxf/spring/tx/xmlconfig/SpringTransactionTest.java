package lxf.spring.tx.xmlconfig;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lxf.spring.tx.xmlconfig.service.impl.BookShopServiceImpl;
import lxf.spring.tx.xmlconfig.service.interfaces.Cashier;
/**
 * 单元测试spring事务类
 * @author lxf
 *
 */
public class SpringTransactionTest {
    private ApplicationContext ctx = null;
    private BookShopDao bookShopDao = null;
    private BookShopServiceImpl bookService = null;
    private Cashier cashier = null;
    {
        ctx = new ClassPathXmlApplicationContext("applicationContext-xmlconfig-tx.xml");
        bookShopDao = (BookShopDao)ctx.getBean("bookShopDao");
        bookService = (BookShopServiceImpl)ctx.getBean("bookShopService");
        cashier = (Cashier)ctx.getBean("cashier");
    }
    /**
     * 单元测试事务传播行为
     */
    @Test
    public void testTransChuanbo()
    {
        int userId= 1;
        List<Integer> booksID = Arrays.asList(1,2);
        cashier.checkout(userId, booksID);
    }
    /**
     * 单元测试用户购买,如果调用的方法中抛出了异常
     * 在单元测试中不捕获，则单元测试不会通过，如果正常捕获则单元测试会正常通过
     */
    @Test
    public void testBookServiceBuy()
    {
        int userId=2,bookId =2;
        try {
            bookService.buyBook(userId, bookId);
        } catch (Exception e) {
            System.out.println("exception error occured");
        }
    }    
}
