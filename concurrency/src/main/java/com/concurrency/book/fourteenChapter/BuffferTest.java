package com.concurrency.book.fourteenChapter;

import org.junit.Test;

/**
 * 测试有界缓存类
 * Create by liangxifeng on 19-11-4
 */
public class BuffferTest {
    @Test
    public void testPut() {
        GrumpyBoundedBuffer buffer = new GrumpyBoundedBuffer(2);
        try {
            buffer.put(1);
            buffer.put(2);
            buffer.put(3);
        } catch (BufferFullException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
