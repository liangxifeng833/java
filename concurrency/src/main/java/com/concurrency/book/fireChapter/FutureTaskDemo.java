package com.concurrency.book.fireChapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * FutureTask获取线程执行结果练习
 *
 * 使用调用FutureTask的线程先从数据库中加载商品
 * 然后通过get方法获取加载的商品信息
 *
 * Create by liangxifeng on 19-9-7
 */
public class FutureTaskDemo {
    private final FutureTask<List> future =
            new FutureTask<>(new Callable<List>() {
                @Override
                public List call() throws Exception {
                    //模拟从数据库中加载商品
                    List productList = new ArrayList();
                    productList.add("商品1");
                    productList.add("商品2");
                    return productList;
                }
            });
    private final Thread thread = new Thread(future);
    //启动线程方法
    public void start() {
        thread.start();
    }

    //通过future.get()获取线程执行结果
    public List get() {
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 程序执行结果:[商品1, 商品2]
     * @param args
     */
    public static void main(String[] args) {
        FutureTaskDemo futureTaskDemo = new FutureTaskDemo();
        futureTaskDemo.start();
        List proList = futureTaskDemo.get();
        System.out.println(proList);
    }
}
