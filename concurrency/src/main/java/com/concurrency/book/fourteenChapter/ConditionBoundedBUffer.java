package com.concurrency.book.fourteenChapter;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用显示条件变量实现的有界缓存
 * Create by liangxifeng on 19-11-5
 */
public class ConditionBoundedBUffer<T> {
    protected final Lock lock = new ReentrantLock();
    //条件谓词：notFull (count<items.length)
    private final Condition notFull = lock.newCondition();
    //条件谓词：notEmpty (count > 0)
    private final Condition notEmpty = lock.newCondition();
    private final T[] items = (T[])new Object[2]; //假设缓存最多存2个元素
    private int tail,head,count;

    //阻塞并直到不满 notFull
    public void put(T t) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) {
                notFull.await();
            }
            items[tail] = t;
            if (++tail == items.length) {
                tail = 0;
            }
            ++count;
            //唤醒非空条件队列，队列有数据了
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    //阻塞直到不空：notEmpty
    public T take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            T t = items[head];
            if (++head == items.length) {
                head = 0;
            }
            --count;
            //唤醒非满条件队列，队列中有空间了，可以新增数据了
            notFull.signal();
            return t;
        }finally {
            lock.unlock();
        }
    }
}
