package lxf.spring.tx.xmlconfig.service.impl;
import javax.management.RuntimeErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lxf.spring.tx.xmlconfig.AccountException;
import lxf.spring.tx.xmlconfig.BookShopDao;

public class BookShopServiceImpl {
    private BookShopDao bookShopDao;
    public void setBookShopDao(BookShopDao bookShopDao)
    {
        this.bookShopDao = bookShopDao;
    }
    /**
     * 用户买书方法
     * @param userId　用户账户表account表主键
     * @param bookId   书books表主键
     */
    public void buyBook  (int userId, int bookId) 
    {
        //１．获取对应数的单价
        double price = bookShopDao.findBookPriceBookId(bookId);
        //２．减库存
        try {
            bookShopDao.updateBookStock(bookId);
        } catch (Error e1) {
            System.out.println("book stock error");
            //e1.printStackTrace();
            throw new RuntimeException(e1);
        }
        //３．扣除用户账户余额
        try {
            bookShopDao.updateUserAccount(userId, price);
        } catch (AccountException e) {
            throw new RuntimeException(e);
        }
    }
}
