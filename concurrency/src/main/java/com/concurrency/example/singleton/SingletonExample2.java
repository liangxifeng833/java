package com.concurrency.example.singleton;

import com.concurrency.annoations.NotThreadSafe;
import com.concurrency.annoations.ThreadSafe;

/**
 * 饿汉模式: 单例的实例在类装载的时候时候被创建 (线程安全)
 * 使用该模式的时候需要注意两个问题:
 * 1.私有构造函数没有太多太耗时的处理
 * 2.类在今后的程序中肯定会被使用,否则会造成资源浪费(类已经装载但没有使用)
 * Create by liangxifeng on 19-7-18
 */
@ThreadSafe
public class SingletonExample2 {
    // 私有化构造方法
    private SingletonExample2(){

    }

    // 单例的对象
    private static SingletonExample2 instance = new SingletonExample2();

    //静态工厂方法获取单例对象
    public static SingletonExample2 getInstance() {
        return instance;
    }
}
