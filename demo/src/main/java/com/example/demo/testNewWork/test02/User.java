package com.example.demo.testNewWork.test02;

import java.io.Serializable;

/**
 * @author liangxifeng
 * @date 2022/11/6 11:06
 */
public class User implements Serializable {
    private static final long serialVersionUID = -8979072804064809970L;
    private String name;
    private String pwd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public User(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
