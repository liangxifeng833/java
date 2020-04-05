package com.concurrency.book.fourteenChapter;

/**
 * 第一实现有界缓存的实现类
 * 当不满足前提条件时，有界缓存不会执行相应的操作
 * Create by liangxifeng on 19-11-4
 */
public class GrumpyBoundedBuffer<V> extends BaseBoundedBuffer<V> {
    protected GrumpyBoundedBuffer(int size) {
        super(size);
    }

    public synchronized void put(V v) throws BufferFullException {
        if (isFull()) {
            throw new BufferFullException("缓存已满不可新增数据");
        }
        super.doPut(v);
    }

    public synchronized V take() throws BufferEmptyException {
        if (isEmpty()) {
            throw new BufferEmptyException("缓存为空，无法取出数据");
        }
        return super.doTake();
    }
}
