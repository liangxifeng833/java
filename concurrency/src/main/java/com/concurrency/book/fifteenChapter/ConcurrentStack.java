package com.concurrency.book.fifteenChapter;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 使用AtomicReference方式实现非阻塞栈的实现
 * 2019-11-26 liangxifeng
 */
@Slf4j
public class ConcurrentStack<E> {
    public AtomicReference<Node<E>> top = new AtomicReference<Node<E>>();
    //入栈
    public void push(E item){
        Node<E> newHead = new Node<E>(item);
        Node<E> oldHead;
        do {
            oldHead = top.get();
            newHead.next = oldHead;
        } while (!top.compareAndSet(oldHead,newHead));
    }
    //出栈
    public E pop () {
        Node<E> oldHead;
        Node<E> newHead;
        do {
            oldHead = top.get();
            if (oldHead == null) {
                return null;
            }
            newHead = oldHead.next;
        } while(!top.compareAndSet(oldHead,newHead));
        return oldHead.item;
    }

    //栈中的节点类
    private static class Node<E> {
        public final E item;
        public Node<E> next;
        public Node (E item) {
            this.item = item;
        }
    }

    /**
     * VM参数： -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+printGCDetails -XX:SurvivorRatio=8
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("++++++++++++++12121212");
        int threadTotal = 100;
        ConcurrentStack stack = new ConcurrentStack<Integer>();
        //定义线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //定义信号量
        final Semaphore semaphore = new Semaphore(10);
        //定义计数器闭锁
        final CountDownLatch countDownLatch = new CountDownLatch(threadTotal);
        for (int i = 0; i < threadTotal; i++) {
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire(); //判断过当前进程是否允许被执行
                    stack.push(count);
                } catch (InterruptedException e) {
                    log.error("exception",e);
                    e.printStackTrace();
                } finally {
                    semaphore.release(); //执行完毕后释放当前这个进程
                }
                //进程执行结束后,计数-1
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        for (int i = 0; i < threadTotal; i++) {
            System.out.println("item = "+stack.pop());
        }
    }

}



