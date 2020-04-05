package com.lxf.concurrence;

/**
 * Description: 线程封闭-Threadlocal的使用联系
 * Create by liangxifeng on 19-7-9
 */
public class ThreadLocalTest {
    /**
     * 自定义一个计数器count
     */
    private ThreadLocal<Integer> count = new ThreadLocal<Integer>() {
        /**
         * 第一次调用count.get()获取值的时候会抛空指针异常
         * 所以在这里重写initialValue()方法,当第一次调用get
         * 的时候如果count=null, 则赋值为0
         * @return
         *
         */
        @Override
        protected Integer initialValue() {
            return new Integer(0);
        }
    };

    /**
     * 计数器++
     * @return
     */
    public int getNext () {
        Integer value = count.get();
        value ++;
        count.set(value);
        return value;
    }

    /**
     * 执行结果:
     *  Thread-0, 1
        Thread-1, 1
        Thread-1, 2
        Thread-0, 2
        Thread-1, 3
        Thread-1, 4
        Thread-0, 3
        Thread-1, 5
        Thread-0, 4
        Thread-0, 5
     * @param args
     */
    public static void main(String[] args) {
        ThreadLocalTest tlt = new ThreadLocalTest();
        //第一个线程启动
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i<5) {
                    System.out.println(Thread.currentThread().getName() + ", "+tlt.getNext());
                    try {
                        Thread.sleep(1000); //休息一秒钟
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                }
            }
        }).start();

        //第二个线程启动
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i<5) {
                    System.out.println(Thread.currentThread().getName() + ", "+tlt.getNext());
                    try {
                        Thread.sleep(500); //休息半钟
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                }
            }
        }).start();
    }
}
