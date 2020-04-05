package com.concurrency.book.fireChapter.create_cache;

/**
 * 创建一个函数compute(输入类型=A, 输出类型=V)
 * Create by liangxifeng on 19-9-9
 */
public interface Computable<A,V> {
    V compute(A arg) throws InterruptedException;
}
