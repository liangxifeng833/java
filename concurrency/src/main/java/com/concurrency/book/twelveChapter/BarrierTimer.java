package com.concurrency.book.twelveChapter;

/**
 * 关卡计时器
 * Create by liangxifeng on 19-10-30
 */
public class BarrierTimer implements Runnable {
    private boolean started;
    private long startTime, endTime;

    @Override
    public void run() {
        long t = System.nanoTime();
        if (!started) {
            started = true;
            startTime = t;
            System.out.println("计时开始，startTime="+this.startTime);
        } else {
            endTime = t;
            System.out.println("计时结束，endTime="+endTime);
        }
    }

    public synchronized void clear() {
        started = false;
    }

    public synchronized long getTime() {
        System.out.println("endTime="+this.endTime+",startTime"+this.startTime);
        return endTime - startTime;
    }
}
