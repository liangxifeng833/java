package com.concurrency.book.fourChapter;

/**
 * interrupt异常练习2
 *
 * 当主线程发出interrupt信号的时候，子线程的sleep()被中断，抛出InterruptedException。
 * sleepBabySleep()不处理sleep()抛出的该异常，直接交到上级caller。
 * 上级caller，即doAPseudoHeavyWeightJob()也不处理，继续交给上级caller，最后直接整个线程挂了。
 * 也相当于成功退出了线程。
 * Create by liangxifeng on 19-9-5
 */
public class InterruptDemo2 extends Thread {
    @Override
    public void run() {
        try {
            doAPseudoHeavyWeightJob();
        } catch (InterruptedException e) {
            System.out.println("=.= I(a thread) am dying...");
            e.printStackTrace();
        }
    }

    private void doAPseudoHeavyWeightJob() throws InterruptedException {

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            System.out.println(i);
            // quit if another thread let me interrupt
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Thread interrupted. Exiting...");
                break;
            } else {
                sleepBabySleep();
            }
        }
    }

    private void sleepBabySleep() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Slept for a while!");
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
     =.= I(a thread) am dying...
     IN MAIN:false
     java.lang.InterruptedException: sleep interrupted
     at java.lang.Thread.sleep(Native Method)
     at com.concurrency.book.fourChapter.InterruptDemo2.sleepBabySleep(InterruptDemo2.java:36)
     at com.concurrency.book.fourChapter.InterruptDemo2.doAPseudoHeavyWeightJob(InterruptDemo2.java:30)
     at com.concurrency.book.fourChapter.InterruptDemo2.run(InterruptDemo2.java:14)
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        InterruptDemo2 thread = new InterruptDemo2();
        thread.start();
        Thread.sleep(5000);
        // let me interrupt
        thread.interrupt();
        System.out.println("IN MAIN:" + thread.isInterrupted());
    }
}
