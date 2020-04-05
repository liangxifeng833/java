package com.concurrency.book.fourteenChapter;

/**
 * 使用条件队列方式作用在验证条件上，实现简单的阻塞
 * Create by liangxifeng on 19-11-4
 */
public class BoundedBuffer<V> extends BaseBoundedBuffer<V> {
    //条件谓语：not-full(!isFull)
    //条件谓词：not-empty(!isEmpty)

    protected BoundedBuffer(int capacity) {
        super(capacity);
    }

    //阻塞，直到：not-full
    public synchronized void put(V v) throws InterruptedException {
        while (isFull()) {
            wait();
        }
        doPut(v);
        notifyAll(); //通知阻塞的take()可以取数据，缓存已经不空了
    }

    //阻塞：直到: not-empty
    public synchronized V take() throws InterruptedException {
        while (isEmpty()) {
            wait();
        }
        V v = doTake();
        notifyAll(); //通知阻塞的put()可以新增数据，缓存已经不满了
        return v;
    }
}
