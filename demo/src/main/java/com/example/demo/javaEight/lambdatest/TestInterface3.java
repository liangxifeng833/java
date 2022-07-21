package com.example.demo.javaEight.lambdatest;

/**
 * 多个参数，有返回值的接口
 */
@FunctionalInterface
public interface TestInterface3 {
    int testMethod(int num1, int num2);
}

class Demo3 {
    public static void main(String[] args) {
         TestInterface3 t2 = (x,y)-> 123;
         int res = t2.testMethod(1,2);
        System.out.println(res);
    }
}
