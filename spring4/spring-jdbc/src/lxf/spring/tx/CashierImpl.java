package lxf.spring.tx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 收银台实现类，测试事务的传播行为
 * @author lxf
 */
@Service("cashier")
public class CashierImpl implements Cashier {
    @Autowired
    private BookShopServiceImpl bookShopServiceImpl;
    /**
     * 一个用户买多本书的情况，新增事务
     */
    @Transactional
    @Override
    public void checkout(int userId, List<Integer> booksID) {
        for (Integer bookId : booksID) {
            bookShopServiceImpl.buyBook(userId, bookId);
        }
    }

}
