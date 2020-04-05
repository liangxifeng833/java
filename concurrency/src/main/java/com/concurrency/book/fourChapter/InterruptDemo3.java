package com.concurrency.book.fourChapter;

/**
 * interrupt异常练习3
 * 当主线程发出interrupt信号的时候，子线程的sleep()被中断，抛出InterruptedException。
 *
 * sleepBabySleep()在处理该异常的时候，重新设置interrupt flag为true，
 * 则子线程在检测中断flag的时候，成功退出线程。
 * （当然，如果子线程始终不检查是否被中断了，也永远不会退出。
 * 所以我们在做一个很耗时的操作时，应该有觉悟检查中断状态，以便收到中断信号时退出。）
 */
public class InterruptDemo3 extends Thread {
    @Override
    public void run() {
        doAPseudoHeavyWeightJob();
    }

    private void doAPseudoHeavyWeightJob() {

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

    private void sleepBabySleep() {
        try {
            Thread.sleep(1000);
            System.out.println("Slept for a while!");
        } catch (InterruptedException e) {
            System.out.println("Interruption happens...");
            //重新设置终端标识flag=true
            Thread.currentThread().interrupt();
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
     Interruption happens...
     5
     Thread interrupted. Exiting...
     IN MAIN:false
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        InterruptDemo3 thread = new InterruptDemo3();
        thread.start();
        Thread.sleep(5000);
        // let me interrupt
        thread.interrupt();
        System.out.println("IN MAIN:" + thread.isInterrupted());
    }

}
