package com.concurrency.book.fourteenChapter;

/**
 * 自定义缓存为空的异常类
 * Create by liangxifeng on 19-11-4
 */
public class BufferFullException extends Exception {
    // 提供无参数的构造方法
    public BufferFullException() {
    }

    // 提供一个有参数的构造方法，可自动生成
    public BufferFullException(String message) {
        super(message);// 把参数传递给Throwable的带String参数的构造方法
    }
}
