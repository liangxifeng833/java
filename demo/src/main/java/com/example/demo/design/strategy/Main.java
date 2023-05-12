package com.example.demo.design.strategy;

import java.util.Arrays;

/**
 * @author liangxifeng
 * @date 2023/5/12 11:12
 */

public class Main {
    public static void main(String[] args) {
        Dog[] a = {new Dog(15),new Dog(6),new Dog(5)};
        Sorter sorter = new Sorter();
        sorter.sort(a,new DogComparator());
        System.out.println(Arrays.toString(a));
    }
}
