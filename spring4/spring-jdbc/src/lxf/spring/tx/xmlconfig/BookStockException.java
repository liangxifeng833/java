package lxf.spring.tx.xmlconfig;
/**
 * 库存不够异常
 * @author lxf
 */
public class BookStockException extends RuntimeException{

    public BookStockException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public BookStockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

    public BookStockException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public BookStockException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public BookStockException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }
    
}
