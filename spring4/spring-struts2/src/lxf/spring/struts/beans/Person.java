package lxf.spring.struts.beans;
/**
 * 人员实体类
 * @author lxf
 */
public class Person {
    private String username;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void hello()
    {
        System.out.println("My name is " + username);
    }
}
