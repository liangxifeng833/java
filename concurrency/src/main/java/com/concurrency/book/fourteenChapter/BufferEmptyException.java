package com.concurrency.book.fourteenChapter;

/**
 * 自定义缓存满的异常类
 * Create by liangxifeng on 19-11-4
 */
public class BufferEmptyException extends Exception{
    public BufferEmptyException(){}

    // 提供一个有参数的构造方法，可自动生成
    public BufferEmptyException(String message) {
        super(message);// 把参数传递给Throwable的带String参数的构造方法
    }
}
