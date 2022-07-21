package com.example.demo;

import java.util.*;

public class CompareTest {
    public static void test() {
        int a;
        int b;
        int c;
        a = 1;
        b = 2;
        c = a + b;
    }

    public static void main(String[] args) {
        test();
        // create some user objects
        User u1 = new User("Aaman", 25);
        u1.testAdd();
        User u2 = new User("Joyita", 22);
        User u3 = new User("Suvam", 28);
        User u4 = new User("mahafuj", 25);
        List<User> userList = Arrays.asList(u1, u2, u3, u4);
        System.out.println("Before Sort");
        userList.forEach(System.out::println);
        System.out.println("After Sort");
        Collections.sort(userList, Comparator.comparingInt(User::getAge));
        userList.forEach(System.out::println);

    }
}

class User implements Comparable<User> {
    public String name;
    public int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int compareTo(User u1) {
        return name.compareTo(u1.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void testAdd() {
        System.out.println("heheh");
    }

    @Override
    public String toString() {
        return "User [name=" + name
                + ", age=" + age + "]";
    }
}
