package com.example.demo.javaEight.functionApi;

import java.util.function.Consumer;

/**
 * java8 常用内置函数接口
 1.  消费型接口：Consumer<T>  - void accept(T t)
 2.  供给型接口：Supplier<T>  - T get()
 3.  函数型接口：Function<T,R> - R apply(T t)
 4.  断言型接口：Predicate<T> -  boolean (T t)
 */
class ConsumerDemo {
    public static void bath(int money, Consumer<Integer> spendMoney) {
        spendMoney.accept(money);
    }
    public static void main(String[] args) {
        // 我搞了一个桃村，话费了 = 100
        bath(100, new Consumer<Integer>() {
            @Override
            public void accept(Integer money) {
                System.out.println("我搞了一个套餐,花费了=" + money);
            }
        });

        // 我搞了一个桃村，话费了 = 100
        bath(100,x -> System.out.println("我搞了一个套餐，话费了=" + x));
    }

}
interface SpendMoney {
    void buy(int money);
}