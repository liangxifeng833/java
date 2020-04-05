package com.concurrency.book.fourteenChapter;

/**
 * 使用轮询＋休眠的方式作用在验证条件上，实现简单的阻塞
 * Create by liangxifeng on 19-11-4
 */
public class SleeperBoundBuffer<V> extends BaseBoundedBuffer<V> {
    protected SleeperBoundBuffer(int size) {
        super(size);
    }

    public synchronized void put(V v) throws InterruptedException {
        while (true) {
            if (!isFull()) {
                super.doPut(v);
                return;
            }
            Thread.sleep(1000);
        }
    }

    public synchronized V take() throws InterruptedException {
        while (true) {
            if (!isEmpty()) {
                return super.doTake();
            }
            Thread.sleep(1000);
        }
    }
}
