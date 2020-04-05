package com.concurrency.book.fireChapter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * 信号量使用demo
 *
 * 使用信号量Semaphore将Set变为一个有界阻塞容器
 * 通过将信号量的计数值初始化=容器set的最大值,
 * 向set中add之前需要信号量acquire方法申请需许可(无许可则阻塞等待)
 * set移除remove一个元素后释放一个许可.
 * Create by liangxifeng on 19-9-9
 */
public class SemaphoreDemo<T> {
    //set容器
    private final Set<T> set;
    //信号量将set容器变为一个有界阻塞容器
    private final Semaphore sem;

    //构造器 num = set容器的最大容量(即信号量的许可数量)
    public SemaphoreDemo(int num) {
        this.set = Collections.synchronizedSet(new HashSet<T>());
        //将信号量的计数值初始化=容器的最大值
        this.sem = new Semaphore(num);
    }

    //set.add之前先sem.acquire获取许可
    public boolean add(T o) throws InterruptedException {
        sem.acquire();
        boolean wasAdded = false;
        try {
            wasAdded = set.add(o);
            return wasAdded;
        } finally {
            if (!wasAdded) {
                sem.release();
            }
        }
    }
    //set.remove之后sem.release释放许可
    public boolean remove(Object o) {
        boolean wasRemoved = set.remove(o);
        if (wasRemoved) {
            sem.release();
        }
        return wasRemoved;
    }

    /**
     * 输出:
     * add..i=0
     * add..i=1
     * 代表i=3的时候,acquire一直在等待许可.
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        //定义set容器容量=2
        SemaphoreDemo demo = new SemaphoreDemo(2);

        //1秒后删除元素1,并释放一个许可,输出:add..i=2
//        new Thread(()->{
//            try {
//                Thread.sleep(1000);
//                demo.remove(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();

        //向set容器新增3个元素
        for (int i=0; i<3; i++) {
            demo.add(i);
            System.out.println("add..i="+i);
        }
    }
}
