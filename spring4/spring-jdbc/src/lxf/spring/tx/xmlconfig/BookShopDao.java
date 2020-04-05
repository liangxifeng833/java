package lxf.spring.tx.xmlconfig;

public interface BookShopDao {
    //根据数主键获取书的单价
    public double findBookPriceBookId(Integer bookId);
    
    //跟新书的库存，使书号对应的库存-1
    public void updateBookStock(Integer bookId);
    
    //更新用户账户余额
    public void updateUserAccount(Integer userId,double price);
    
    
}
