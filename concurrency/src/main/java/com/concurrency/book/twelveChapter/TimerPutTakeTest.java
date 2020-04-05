package com.concurrency.book.twelveChapter;

import org.junit.Assert;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 12.1.3 并发测试安全性,用多线程来测试多线程(有统计时间的程序)
 * 尽量在编写测试代码的时候少用同步
 * Create by liangxifeng on 19-10-29
 */
public class TimerPutTakeTest {
    private static final ExecutorService pool = Executors.newCachedThreadPool();
    private final AtomicInteger putSum = new AtomicInteger(0); //入队总数
    private final AtomicInteger takeSum = new AtomicInteger(0);//出队总数
    private final CyclicBarrier barrier;
    private final BoundedBuffer<Integer> bb;
    //nTrials=向有限队列中添加的元素个数，nParis=加入或取出队列的次数
    private final int nTrials, nParis;

    public static void main(String[] args) throws InterruptedException {
        //new PutTakeTest(10, 10, 100000).test(); // sample parameters
        //int tpt = 10000;
        int tpt = 100;
        //for (int cap = 1; cap<=1000; cap*=10) {
        for (int cap = 1; cap<=100; cap*=10) {
            //System.out.println("capacity="+cap);
            for (int paris = 1; paris <= 128; paris*=2) {
                TimerPutTakeTest t = new TimerPutTakeTest(cap,paris,tpt);
                //System.out.println("Pairs="+paris+"\t");
                t.test();
//                System.out.println("\t");
//                Thread.sleep(1000);
//                t.test();
//                System.out.println();
//                Thread.sleep(1000);
            }
        }
        pool.shutdown();
    }

    TimerPutTakeTest(int capacity, int nParis, int nTrials) {
        this.bb = new BoundedBuffer<Integer>(capacity);
        this.nTrials = nTrials;
        this.nParis = nParis;
        this.barrier = new CyclicBarrier(nParis*2+1);
        //this.barrier = new CyclicBarrier(10);
    }

    void test() {
        BarrierTimer timer = new BarrierTimer();
        timer.clear();//每次进来都重新计时
        int nPairs = nParis;
        try {
            pool.execute(timer); //得到程序开始时间
            for (int i = 0; i < nPairs; i++) {
                pool.execute(new Producer());
                pool.execute(new Consumer());
            }
            barrier.await(); // 等待所有线程做好准备
            barrier.await(); // 等待所有线程最终完成
            pool.execute(timer); //得到程序截止时间
            Thread.sleep(1000);
            long nsPerItem = timer.getTime()/(nParis*(long)nTrials); //平均执行时间
            System.out.println("程序执行时间="+nsPerItem+" ns/item");
            //System.out.println("最终入队总数:"+putSum.get());
            //System.out.println("最终出对总数:"+takeSum.get());
            Assert.assertEquals(putSum.get(), takeSum.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static int xorShift(int y) {
        y ^= (y << 6);
        y ^= (y >>> 21);
        y ^= (y << 7);
        return y;
    }

    //生产者
    class Producer implements Runnable {
        public void run() {
            try {
                int seed = (this.hashCode() ^ (int) System.nanoTime());
                int sum = 0;
                barrier.await();
                for (int i = nTrials; i > 0; --i) {
                    bb.put(seed);
                    sum += seed;
                    seed = xorShift(seed);
                }
                putSum.getAndAdd(sum);
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    //消费者
    class Consumer implements Runnable {
        public void run() {
            try {
                barrier.await();
                int sum = 0;
                for (int i = nTrials; i > 0; --i) {
                    sum += bb.take();
                }
                takeSum.getAndAdd(sum);
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
