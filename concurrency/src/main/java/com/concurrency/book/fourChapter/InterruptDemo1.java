package com.concurrency.book.fourChapter;

/**
 * 线程中断测试
 * Create by liangxifeng on 19-9-4
 */
public class InterruptDemo1 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()-> {
            int i = 1;
            //如果该线程的中断标识=false，那么在循环中执行业务逻辑
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("正常执行任务i="+i);
                i++;
            }
            //如果线程的中断标识=true,则退出以上循环，输出如下信息
            //可以在这里进行资源的释放等操作……
            System.out.println("该程序被中断了");
        });
        //启动线程t1
        t1.start();
        Thread.sleep(1000);
        //通知t1线程中断,将t1线程的中断标识设置=true
        t1.interrupt();
    }
}
