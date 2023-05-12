package com.example.demo.design.strategy;

/**
 * @author liangxifeng
 * @date 2023/5/12 11:12
 */

public class Dog {
    public int food;

    public Dog(int food) {
        this.food = food;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "food=" + food +
                '}';
    }
}
