package lxf.spring.tx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
/**
 * 书店dao
 * @author lxf
 */
@Repository("bookShopDao")
public class BookShopDaoImpl implements BookShopDao {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public double findBookPriceBookId(Integer bookId) {
        String sql = "SELECT price from books WHERE book_id = ?";
        return jdbcTemplate.queryForObject(sql, double.class, bookId);
    }

    @Override
    public void updateBookStock(Integer bookId) {
        String sql1 = "SELECT stock from book_stock WHERE book_id= ?";
        double stock = jdbcTemplate.queryForObject(sql1, double.class, bookId);
        //如果库存不足则抛出异常
        if(stock<=0)
        {
            throw new BookStockException("图书库存不足");
        }      
       String sql = "UPDATE book_stock SET stock = stock-1 WHERE book_id = ?";
       jdbcTemplate.update(sql, bookId);
    }

    @Override
    public void updateUserAccount(Integer userId, double price) {
        String sql1 = "SELECT balance from acount WHERE id= ?";
        int balance = jdbcTemplate.queryForObject(sql1, Integer.class, userId);
        //如果用户余额不足则抛出异常
        if(balance<=0)
        {
            throw new AccountException("用户余额不足!");
        }
        String sql = "UPDATE acount SET balance=balance-? WHERE id = ?";
        jdbcTemplate.update(sql, price, userId);
    }
}
