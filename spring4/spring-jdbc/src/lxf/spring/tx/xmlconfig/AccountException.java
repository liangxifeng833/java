package lxf.spring.tx.xmlconfig;
/**
 * 用户余额不足异常
 * @author lxf
 */
public class AccountException extends RuntimeException{
    public AccountException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public AccountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

    public AccountException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public AccountException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public AccountException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }  
}
