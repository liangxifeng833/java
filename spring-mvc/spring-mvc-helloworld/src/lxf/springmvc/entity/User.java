package lxf.springmvc.entity;
/**
 * 用户实体类
 * @author lxf
 */
public class User {
    private int id;
    private String username;
    private String password;
    private Address address;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    @Override
    public String toString() {
        return "User [username=" + username + ", password=" + password + ", address=" + address + "]";
    }
    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }
    
    public User(int id, String username, String password, Address address) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.address = address;
    }
    public User(){}
}
