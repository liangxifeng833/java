package com.example.demo.design.strategy;

/**
 * 自定义比较器
 * @author liangxifeng
 * @date 2023/5/12 11:02
 */
public interface IComparator<T> {
    int compare(T o1, T o2);
}
