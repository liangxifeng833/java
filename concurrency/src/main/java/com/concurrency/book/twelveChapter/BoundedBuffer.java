package com.concurrency.book.twelveChapter;

import java.util.Arrays;
import java.util.concurrent.Semaphore;

/**
 * 实现一个基于数组的定长队列
 * Create by liangxifeng on 19-10-29
 */
public class BoundedBuffer<E> {
    //从缓存中删除的元素个数,初始值=0, 因为缓存初始状态为空
    private final Semaphore availableItems;
    //可以插入到缓存的元素最大个数,初始值=缓存大小
    private final Semaphore availableSpaces;
    private final E[] items;
    private int putPosition = 0, takePosition = 0;

    public BoundedBuffer(int capacity) {
        availableItems = new Semaphore(0);
        availableSpaces = new Semaphore(capacity);
        items = (E[])new Object[capacity];
    }

    public boolean isEmpty() {
        return availableItems.availablePermits() == 0;
    }

    public boolean isFull() {
        return availableSpaces.availablePermits() == 0;
    }

    public void put(E x) throws InterruptedException {
        availableSpaces.acquire();
        doIndex(x);
        availableItems.release();
        //System.out.println("可删除的许可数量:"+availableItems.availablePermits());
    }

    private synchronized void doIndex(E x) {
        int i = putPosition;
        items[i] = x;
        //System.out.println(Arrays.asList(items));
        putPosition = (++i == items.length) ? 0 : i;
    }

    public E take() throws InterruptedException {
        availableItems.acquire();
        E item = doExtract();
        availableSpaces.release();
        return item;
    }

    private synchronized E doExtract() {
        int i= takePosition;
        E x = items[i];
        items[i] = null;
        takePosition = (++i == items.length) ? 0 : i;
        return x;
    }
}
