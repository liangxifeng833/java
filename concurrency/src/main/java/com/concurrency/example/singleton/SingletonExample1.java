package com.concurrency.example.singleton;

import com.concurrency.annoations.NotThreadSafe;

import java.text.SimpleDateFormat;

/**
 * 懒汉模式: 单例的实例在第一次使用的时候被创建(线程不安全)
 * Create by liangxifeng on 19-7-18
 */
@NotThreadSafe
public class SingletonExample1 {
    // 私有化构造方法
    private SingletonExample1(){}

    // 单例的对象
    private static SingletonExample1 instance = null;

    //静态工厂方法获取单例对象
    public static SingletonExample1 getInstance() {
        if (instance == null) {
            instance = new SingletonExample1();
        }
        return instance;
    }
}
