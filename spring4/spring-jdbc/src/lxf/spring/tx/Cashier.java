package lxf.spring.tx;
import java.util.List;

/**
 * 收银台接口
 * @author lxf
 */
public interface Cashier { 
    public void checkout(int userId, List<Integer>booksID);
}
