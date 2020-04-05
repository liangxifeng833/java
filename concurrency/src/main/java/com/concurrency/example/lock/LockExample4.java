package com.concurrency.example.lock;

import com.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

/**
 * 使用java.util.concurrent中的lock的condition条件变量的使用
 * Create by liangxifeng on 19-7-30
 */
@Slf4j
@ThreadSafe
public class LockExample4 {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        new Thread( () -> {
            try {
                reentrantLock.lock();
                log.info("wait signal"); //1
                condition.await();  //从AQS等待队列中出队, 进入condition条件变量的等待队列中
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("get signal");  //4 下面condition.signalAll()通知后的操作
            reentrantLock.unlock();
        }).start();

        new Thread( () -> {
            reentrantLock.lock();
            log.info("get lock"); //2
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            condition.signalAll();  //通知condition条件变量等待队列中的成员出队,继续执行
            log.info("send signal ~~"); //3
            reentrantLock.unlock();
        }).start();



    }

}
