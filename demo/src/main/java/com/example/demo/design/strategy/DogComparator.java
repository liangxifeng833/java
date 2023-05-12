package com.example.demo.design.strategy;

/**
 * @author liangxifeng
 * @date 2023/5/12 11:14
 */

public class DogComparator implements IComparator<Dog>{

    @Override
    public int compare(Dog o1, Dog o2) {
        if(o1.food < o2.food) return -1;
        else if (o1.food > o2.food) return 1;
        else return 0;
    }
}
