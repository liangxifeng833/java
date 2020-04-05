package com.lxf.po;

import java.util.List;

/**
 * 用来在action中接收用户表单提交过来的数据
 * @author lxf
 *
 */

public class User {
    private String username;
    private String password;
    //接收form表单中的图书字符串数组
    private List<String> bookList;
     //接收form表单中的用户对象数组
    private List<User>userList;
    //年龄
    private int age;
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
    public List<String> getBookList() {
        return bookList;
    }
    public void setBookList(List<String> bookList) {
        this.bookList = bookList;
    }
    public List<User> getUserList() {
        return userList;
    }
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    
    
}
