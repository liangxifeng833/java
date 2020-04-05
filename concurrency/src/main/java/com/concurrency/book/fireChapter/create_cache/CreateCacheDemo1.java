package com.concurrency.book.fireChapter.create_cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * 使用ConcurrentHashMap方式实现缓存
 *
 * 但是该方式存在缺点,当两个线程同时调用缓中没有存在的key时
 * 都会去新计算结果,如果新计算的很长,那么可能存在两个线程计算结果相同的情况.
 * Create by liangxifeng on 19-9-9
 */
public class CreateCacheDemo1 <A,V> implements Computable<A,V>{
    private final Map<A,V> cache = new ConcurrentHashMap<A,V>();

    public final Computable<A,V> c;

    CreateCacheDemo1(Computable<A,V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg,result);
        }
        return result;
    }

    public Map getCache() {
        return cache;
    }

    public static void main(String[] args) throws InterruptedException {
        ExpensiveFunction a = new ExpensiveFunction();
        CreateCacheDemo1 createCacheDemo = new CreateCacheDemo1(a);
        CountDownLatch count = new CountDownLatch(10);
        for(int i=0; i<10; i++) {
            System.out.println("i="+i);
            int finalI = i;
            new Thread(()->{
                try {
                    createCacheDemo.compute(String.valueOf(finalI));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    count.countDown();
                }
            }).start();
        }
        count.await();
        System.out.println(createCacheDemo.getCache());
    }
}
