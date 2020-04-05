package com.concurrency.example.guavaCache;

import java.util.concurrent.ExecutionException;

/**
 * Description: myFirst
 * Create by liangxifeng on 19-8-13
 */
public class Test2 {
    public static void main(String[] args) {
        GuavaCachDemo2 cachDemo = new GuavaCachDemo2();
        cachDemo.Init();

        System.out.println("使用loadingCache");
        cachDemo.InitLoadingCache();

        System.out.println("使用loadingCache get方法  第一次加载");
        Man man = cachDemo.getCacheKeyloadingCache("001");
        System.out.println(man);

        System.out.println("\n使用loadingCache getIfPresent方法  第一次加载");
        man = cachDemo.getIfPresentloadingCache("002");
        System.out.println(man);

        System.out.println("\n使用loadingCache get方法  第一次加载");
        man = cachDemo.getCacheKeyloadingCache("002");
        System.out.println(man);

        System.out.println("\n使用loadingCache get方法  已加载过");
        man = cachDemo.getCacheKeyloadingCache("002");
        System.out.println(man);

        System.out.println("\n使用loadingCache get方法  已加载过,但是已经被剔除掉,验证重新加载");
        man = cachDemo.getCacheKeyloadingCache("001");
        System.out.println(man);

        System.out.println("\n使用loadingCache getIfPresent方法  已加载过");
        man = cachDemo.getIfPresentloadingCache("001");
        System.out.println(man);

        System.out.println("\n使用loadingCache put方法  再次get");
        Man newMan = new Man();
        newMan.setId("001");
        newMan.setName("额外添加");
        cachDemo.putloadingCache("001",newMan);
        man = cachDemo.getCacheKeyloadingCache("001");
        System.out.println(man);

        ///////////////////////////////////
        System.out.println("\n\n使用Cache");
        cachDemo.InitDefault();

        System.out.println("使用Cache get方法  第一次加载");
        try {
            man = cachDemo.getCacheKeyCache("001");
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(man);

        System.out.println("\n使用Cache getIfPresent方法  第一次加载");
        man = cachDemo.getIfPresentCache("002");
        System.out.println(man);

        System.out.println("\n使用Cache get方法  第一次加载");
        try {
            man = cachDemo.getCacheKeyCache("002");
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(man);

        System.out.println("\n使用Cache get方法  已加载过");
        try {
            man = cachDemo.getCacheKeyCache("002");
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(man);

        System.out.println("\n使用Cache get方法  已加载过,但是已经被剔除掉,验证重新加载");
        try {
            man = cachDemo.getCacheKeyCache("001");
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(man);

        System.out.println("\n使用Cache getIfPresent方法  已加载过");
        man = cachDemo.getIfPresentCache("001");
        System.out.println(man);

        System.out.println("\n使用Cache put方法  再次get");
        Man newMan1 = new Man();
        newMan1.setId("001");
        newMan1.setName("额外添加");
        cachDemo.putloadingCache("001",newMan1);
        man = cachDemo.getCacheKeyloadingCache("001");
        System.out.println(man);
    }
}
