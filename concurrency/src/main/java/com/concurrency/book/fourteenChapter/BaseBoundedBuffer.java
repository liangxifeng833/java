package com.concurrency.book.fourteenChapter;

/**
 * 有界缓存实现的基类
 * Create by liangxifeng on 19-11-4
 */
public class BaseBoundedBuffer<V> {
    private final V[] buff;
    private int tail;
    private int head;
    private int count;

    protected BaseBoundedBuffer(int capacity) {
        this.buff = (V[])new Object[capacity];
    }

    //向缓存中新增数据
    protected synchronized final void doPut(V v) {
        buff[tail] = v;
        if (++tail == buff.length) {
            tail = 0;
        }
        System.out.println("tail="+tail+", buff.length="+buff.length);
        ++count;
    }

    //从缓存中取出数据
    protected synchronized final V doTake() {
        V v = buff[head];
        buff[head] = null;
        if (++head == buff.length) {
            head = 0;
        }
        --count;
        return v;
    }

    //判断缓存是否已满
    public synchronized final boolean isFull() {
        return count == buff.length;
    }

    //判断缓存是否为空
    public synchronized final boolean isEmpty() {
        return count == 0;
    }
}
