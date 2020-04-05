package lxf.spring.hibernate.entiries;
/**
 * 账户实体类
 * @author lxf
 *
 */
public class Acount {
    //账户主键
    private int id;
    //账户名
    private String user_name;
    //账户余额
    private double balance;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUser_name() {
        return user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    
}
