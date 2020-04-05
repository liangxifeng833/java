package lxf.spring.tx;
import javax.management.RuntimeErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookShopServiceImpl {
    
    @Autowired
    private BookShopDao bookShopDao;
    /**
     * 用户买书方法
     * @param userId　用户账户表account表主键
     * @param bookId   书books表主键
     */
    /**
     * 添加事务注解,只在在执行的方法中执行多个业务罗辑之间有一个抛出异常则会执行事务
     * 如果有异常但是不抛出，则事务不起作用
     * １．使用propagation指定事务的传播行为，即当前的事务方法被另外一个事务方法调用时
     *     如何使用事务,默认取值为REQUIRED，即使用调用方法的事务
     *     REQUIRED_NEW：事务自己的事务，调用的事务方法的事务被挂起；
     * ２．指定事务的隔离级别属性,最常用的取值是：isolation=Isolation.READ_COMMITTED
     * ３．默认情况下Spring的声明事务对所有的运行时异常进行回滚，
     *        也可以通过对应的属性进行设置，通常情况下取默认值即可,
     *        比如：指定发生AccountException异常不回滚,noRollbackFor={AccountException.class}
     * ４．使用readOnly指定事务是否是可读，若只读取数据库的方法，
     *         应设置readOnly=true，可以帮助数据库引擎优化事务．
     * ５．使用timeout属性指定强制回滚之间事务可以占用的时间，单位为：秒
     */
    @Transactional(propagation=Propagation.REQUIRES_NEW,
                                isolation=Isolation.READ_COMMITTED,
                                readOnly=false,
                                timeout=5)
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
