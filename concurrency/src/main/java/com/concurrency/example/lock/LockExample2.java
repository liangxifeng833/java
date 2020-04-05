package com.concurrency.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 使用java.util.concurrent中的ReentrantReadWriteLock练习
 * Create by liangxifeng on 19-7-30
 */
@Slf4j
public class LockExample2 {
    private final Map<String, Data> map = new TreeMap<>();

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock(); //读锁

    private final Lock writeLock = lock.writeLock(); //写锁

    public Data get(String key) {
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public Set<String> getAllKeys() {
        readLock.lock();
        try {
            return map.keySet();
        } finally {
            readLock.unlock();
        }
    }

    public Data put(String key, Data value) {
        writeLock.lock();
        try {
            return map.put(key,value);
        } finally {
            writeLock.unlock();
        }

    }

    public static void main(String[] args) throws InterruptedException {

    }

    class Data{}

}
