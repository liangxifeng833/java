package com.example.demo.javaEight.lambdatest;

/**
 * 有参无返回值
 */
public interface TestInterface2 {
    void testMethod(int num);
}

class Demo02 {
    public static void main(String[] args) {
         TestInterface2 t2 = (x)-> System.out.println("我是Lambda表达式实现的testMethod(),num="+x);
         t2.testMethod(100);
    }
}
