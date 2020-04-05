package com.concurrency.example.singleton;

import com.concurrency.annoations.NotRecommend;
import com.concurrency.annoations.ThreadSafe;

/**
 * 懒汉模式: 单例的实例在第一次使用的时候被创建(线程安全)
 * 使用synchronized 将懒汉模式变为线程安全,但是会有性能损耗
 * Create by liangxifeng on 19-7-18
 */
@ThreadSafe
@NotRecommend
public class SingletonExample3 {
    // 私有化构造方法
    private SingletonExample3(){}

    // 单例的对象
    private static SingletonExample3 instance = null;

    //静态工厂方法获取单例对象 (使用synchronized修饰变为线程安全)
    public synchronized static SingletonExample3 getInstance() {
        if (instance == null) {
            instance = new SingletonExample3();
        }
        return instance;
    }
}
