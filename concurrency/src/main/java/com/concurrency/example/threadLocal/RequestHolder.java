package com.concurrency.example.threadLocal;


/**
 * Description: myFirst
 * Create by liangxifeng on 19-7-25
 */
public class RequestHolder {
    private final static ThreadLocal<Long> requestHolder =  new ThreadLocal<Long>();

    public static void add(Long id) {
        requestHolder.set(id);
    }

    public static Long getId() {
        return requestHolder.get();
    }

    public static void remove() {
        requestHolder.remove();
    }
}

