package lxf.spring.hibernate.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lxf.spring.hibernate.dao.BookShopDao;
import lxf.spring.hibernate.dao.impl.BookShopDaoImpl;
import lxf.spring.hibernate.service.BookShopService;

/**
 * 买书service实现类
 * @author lxf
 *
 */
@Service("bookShopService")
public class BookShopServiceImpl  implements BookShopService{
    @Autowired
    private BookShopDao bookShopDao;
    /**
     * Spring hibernate事务流程
     * １．在方法开始之前
     * （１）获取CurrentSession
     * （２）把Session和当前线程绑定，这样就可以在Dao中使用
     *              SessionFactory的getCurrentSession方法获取Session了
     * （３）开启事务
     * 
     * ２．若方法正常结束，则没有出现异常，则
     * （１）提交事务
     * （２）使和当前线程的Session解除绑定
     * （３）关闭Session
     * 
     * ３．若方法出现异常，则：
     * （１）回滚事务
     * （２）使和当前线程的Session解除绑定
     * （３）关闭Session
     */
    /**
     * 用户买书方法
     * @param userId　用户账户表account表主键
     * @param bookId   书books表主键
     */
    public void buyBook  (int userId, int bookId) 
    {
       //System.out.println(bookShopDao.getSession());
        //１．获取对应数的单价
        double price = bookShopDao.findBookPriceBookId(bookId);
        //２．减库存
        bookShopDao.updateBookStock(bookId);
        //３．扣除用户账户余额
         bookShopDao.updateUserAccount(userId, price);
    }
}
