package com.concurrency.example.singleton;

import com.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 饿汉模式: 单例的实例在类装载的时候时候被创建 (线程安全)
 * 使用静态代码块的方式实现
 * Create by liangxifeng on 19-7-18
 */
@ThreadSafe
@Slf4j
public class SingletonExample6 {
    // 私有化构造方法
    private SingletonExample6(){

    }

    // 单例的对象, 注意该行代码必须在static静态块之前,否则最终会导致instance = null
    private static SingletonExample6 instance = null;

    static {
        instance = new SingletonExample6();
    }

    //静态工厂方法获取单例对象
    public static SingletonExample6 getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        log.info("{}",getInstance().hashCode()); //1555093762
        log.info("{}",getInstance().hashCode()); //1555093762
    }
}
