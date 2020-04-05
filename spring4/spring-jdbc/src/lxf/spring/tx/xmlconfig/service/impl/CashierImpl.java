package lxf.spring.tx.xmlconfig.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lxf.spring.tx.xmlconfig.service.interfaces.Cashier;
/**
 * 收银台实现类，测试事务的传播行为
 * @author lxf
 */
public class CashierImpl implements Cashier {
    private BookShopServiceImpl bookShopServiceImpl;
    public void setBookShopServiceImpl( BookShopServiceImpl bookShopServiceImpl)
    {
        this.bookShopServiceImpl = bookShopServiceImpl;
    }
    /**
     * 一个用户买多本书的情况，新增事务
     */
    public void checkout(int userId, List<Integer> booksID) {
        for (Integer bookId : booksID) {
            bookShopServiceImpl.buyBook(userId, bookId);
        }
    }

}
