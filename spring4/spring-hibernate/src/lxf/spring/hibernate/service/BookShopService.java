package lxf.spring.hibernate.service;
/**
 * 图书销售service接口
 * @author lxf
 */
public interface BookShopService {
    //用户买书方法
    public void buyBook(int userId, int bookId);
}
