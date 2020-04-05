package lxf.spring.hibernate.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lxf.spring.hibernate.dao.BookShopDao;
import lxf.spring.hibernate.exception.BookStockException;
import lxf.spring.hibernate.exception.UserAcountException;

@Repository("bookShopDao")
public class BookShopDaoImpl implements BookShopDao {
      
    @Autowired
    private SessionFactory sessionFactory;
    
    //获得和当前线程绑定的session
    public Session getSession()
    {
       return sessionFactory.getCurrentSession();
    }

    @Override
    public double findBookPriceBookId(Integer bookId) {
        String hql ="SELECT b.price FROM Books b WHERE b.book_id = ?";
        Query query = getSession().createQuery(hql).setInteger(0, bookId);
        return (double)query.uniqueResult();
    }

    @Override
    public void updateBookStock(Integer bookId) {
           //先查询是否有库存
            String hql ="SELECT b.stock  FROM Books b WHERE b.book_id= ?";
            Query query = getSession().createQuery(hql).setInteger(0, bookId);
            int stock = (int)query.uniqueResult();
            if(stock <= 0)
            {
                throw new BookStockException("图书库存不足!");
            }
            //修改库存
            String hql2 = "UPDATE Books b SET b.stock =b. stock-1 WHERE b.book_id = ?";
            getSession().createQuery(hql2).setInteger(0, bookId).executeUpdate();           
    }

    @Override
    public void updateUserAccount(Integer userId, double price) {
        //先查询账户余额是否够
        String hql ="SELECT a.balance  FROM Acount a WHERE a.id = ?";
        Query query = getSession().createQuery(hql).setInteger(0, userId);
        double balance = (double)query.uniqueResult();
        if(balance <= 0)
        {
            throw new UserAcountException("用户账户余额不足！");
        }
        //修改账户余额
        String hql2 = "UPDATE Acount a SET a.balance = a.balance-? WHERE a.id = ?";
        getSession().createQuery(hql2).setDouble(0, price).setInteger(1, userId).executeUpdate();        
    }
}
