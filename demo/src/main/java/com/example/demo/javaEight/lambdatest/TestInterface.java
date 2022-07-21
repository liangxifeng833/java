package com.example.demo.javaEight.lambdatest;

/**
 * 无参无返回值的接口
 */
public interface TestInterface {
    void testMethod();
}

class MyClass implements TestInterface {
    @Override
    public void testMethod() {
        System.out.println("我重新实现了testMethod方法");
    }
}

class Demo {
    public static void main(String[] args) {
        TestInterface t = new MyClass();
        t.testMethod();

        //匿名内部类的方式实现接口, 匿名内部类该类使用的地方很少，所以在一个地方定义
        TestInterface t1 = new TestInterface() {
            @Override
            public void testMethod() {
                System.out.println("我是匿名内部类的testMethod方法()");
            }
        };
         t1.testMethod();

         TestInterface t2 = ()-> System.out.println("我是Lambda表达式实现的testMethod()");
         t2.testMethod();
    }
}
