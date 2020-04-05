package com.concurrency.book.fourChapter;

/**
 * interrupt异常练习4
 * 主线程发出interrupt信号的时候，子线程的sleep()被中断，抛出InterruptedException。
 *
 * sleepBabySleep()在处理该异常的时候，直接把该异常吞了，
 * sleepBabySleep方法既没有throw异常，也没有重新设置interrupt flag为true。
 * 此时interrupt flag为false，在子线程中检测中断flag的时候，不能成功退出线程，
 * 直到i=11的时候，该子线程将自己的interrupt flag设为true，才再次在检查中断的时候，成功退出子线程
 *
 * Create by liangxifeng on 19-9-5
 */
public class InterruptDemo4 extends Thread {
    @Override
    public void run() {
        doAPseudoHeavyWeightJob();
    }

    private void doAPseudoHeavyWeightJob() {

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            System.out.println(i);
            // 当大于10时，直接自我了断
            if (i > 10) {
                Thread.currentThread().interrupt();
            }
            // quit if another thread let me interrupt
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Thread interrupted. Exiting...");
                break;
            } else {
                sleepBabySleep();
            }
        }
    }

    private void sleepBabySleep() {
        try {
            Thread.sleep(1000);
            System.out.println("Slept for a while!");
        } catch (InterruptedException e) {
            System.out.println("Interruption happens... But I do nothing.");
            Thread.currentThread().interrupt();
            new InterruptedException();
        }
    }

    /**
     * 输出：
     0
     Slept for a while!
     1
     Slept for a while!
     2
     Slept for a while!
     3
     Slept for a while!
     4
     Interruption happens... But I do nothing.
     5
     IN MAIN:false
     Slept for a while!
     6
     Slept for a while!
     7
     Slept for a while!
     8
     Slept for a while!
     9
     Slept for a while!
     10
     Slept for a while!
     11
     Thread interrupted. Exiting...
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        InterruptDemo4 thread = new InterruptDemo4();
        thread.start();
        Thread.sleep(5000);
        // let me interrupt
        thread.interrupt();
        System.out.println("IN MAIN:" + thread.isInterrupted());
    }
}
