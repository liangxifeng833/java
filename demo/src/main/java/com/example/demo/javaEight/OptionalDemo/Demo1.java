package com.example.demo.javaEight.OptionalDemo;


import java.util.Optional;

/**
 * @author liangxifeng
 * @date 2022/7/12 10:47
 */

public class Demo1 {

    public static void main(String[] args) {
        User user = null;
        user = Optional.ofNullable(user).orElse(createUser());
        user = Optional.ofNullable(user).orElseGet(Demo1::createUser);
        System.out.println(user);
    }

    public static User createUser() {
        User user = new User();
        user.setName("hahah");
        return user;
    }

}
