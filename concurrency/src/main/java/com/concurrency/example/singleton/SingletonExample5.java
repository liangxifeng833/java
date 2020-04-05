package com.concurrency.example.singleton;

import com.concurrency.annoations.ThreadSafe;

/**
 * 懒汉模式 => 双重同步锁单例模式
 * 单例实例在第一次使用的时候进行创建
 * volatile+双重检测机制禁止指令重排导致的线程不安全情况
 * Create by liangxifeng on 19-7-18
 */
@ThreadSafe
public class SingletonExample5 {
    // 私有化构造方法
    private SingletonExample5(){}

    // 单例的对象
    private static volatile SingletonExample5 instance = null;

    //静态工厂方法获取单例对象
    public static SingletonExample5 getInstance() {
        if (instance == null) { //使用双重检测机制
            synchronized (SingletonExample5.class) { //同步锁
                if (instance == null) {
                    instance = new SingletonExample5();
                    // cpu指令执行顺序如下:
                    //1. memory = allocate() 分配内存空间
                    //2. ctorInstance() 初始化对象
                    //3. instance = memory 设置instance指向刚刚分配的内存空间

                    //因为instance已被声明为volatile类型, 所以JVM和cpu不会进行指令重排序,因此线程安全
                }
            }

        }
        return instance;
    }
}
