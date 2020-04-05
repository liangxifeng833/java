package com.concurrency.example.singleton;

import com.concurrency.annoations.Recommend;
import com.concurrency.annoations.ThreadSafe;
/**
 * 枚举模式: 第一次使用的时候创建单例的实例,(最安全的方式)
 * 使用静态代码块的方式实现
 * Create by liangxifeng on 19-7-18
 */
@ThreadSafe
@Recommend
public class SingletonExample7 {
    // 私有化构造方法
    private SingletonExample7(){

    }

    public static SingletonExample7 getInstance() {
        return Singleton.INSTANCE.getSingleton();
    }

    private enum Singleton {
        INSTANCE;
        private SingletonExample7 singleton;

        // JVM保证这个方法只执行一次
        Singleton() {
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getSingleton() {
            return singleton;
        }
    }
}
