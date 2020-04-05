package com.concurrency.book.twelveChapter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义的随机数生成器
 * Create by liangxifeng on 19-10-29
 */
public class XorShift {
    static final AtomicInteger seq = new AtomicInteger(8862213);
    int x = -1831433054;

    public XorShift(int seed) {
        x = seed;
    }

    public XorShift() {
        this((int) System.nanoTime() + seq.getAndAdd(129));
    }

    public int next() {
        x ^= x << 6;
        x ^= x >>> 21;
        x ^= (x << 7);
        return x;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            //System.out.println(System.nanoTime());
            XorShift xorShift = new XorShift();
            int a = xorShift.next();
            int b = a < 0 ? (-a) : a;
            System.out.println(b);
        }
    }
}
