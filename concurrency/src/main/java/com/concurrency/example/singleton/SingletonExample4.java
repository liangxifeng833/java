package com.concurrency.example.singleton;


import com.concurrency.annoations.NotThreadSafe;

/**
 * 懒汉模式 => 双重同步锁单例模式
 * 单例实例在第一次使用的时候进行创建
 * Create by liangxifeng on 19-7-18
 */
@NotThreadSafe
public class SingletonExample4 {
    // 私有化构造方法
    private SingletonExample4(){}

    // 单例的对象
    private static SingletonExample4 instance = null;

    //静态工厂方法获取单例对象
    public  static SingletonExample4 getInstance() {
        if (instance == null) { //使用双重检测机制
            synchronized (SingletonExample4.class) { //同步锁
                if (instance == null) {
                    instance = new SingletonExample4();
                    // cpu指令执行顺序如下:
                    //1. memory = allocate() 分配内存空间
                    //2. ctorInstance() 初始化对象
                    //3. instance = memory 设置instance指向刚刚分配的内存空间

                    //但是实际上JVM和cpu会对指令顺序进行重排,cpu指令真正执行的顺序可能是如下情况:
                    //1. memory = allocate() 分配内存空间
                    //3. instance = memory 设置instance指向刚刚分配的内存空间
                    //2. ctorInstance() 初始化对象
                    //当A线程执行到3, 此时进行了任务切换B线程开始执行,此时B线程发现instance不为null,
                    //便开始使用该实例,但是实际上该该实例还没有被初始化操作,所以会出现问题(线程不安全)
                }
            }

        }
        return instance;
    }
}
