package com.concurrency.book.twelveChapter;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 基于数组的定长队列的单元测试
 * Create by liangxifeng on 19-10-29
 */
public class BoundedBufferTest {
    //单元测试基于数组的定长队列,初始状态
    @Test
    public void testIsEmptyWhenConstructed() {
        BoundedBuffer<Integer> bb =new BoundedBuffer<Integer>(2);
        //初始状态为空
        Assert.assertTrue(bb.isEmpty());
        //初始状态不满
        Assert.assertFalse(bb.isFull());
    }

    //在put元素后队列已满之后的单元测试
    @Test
    public void testIfFullAfterPuts() {
        BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(2);
        List<Integer> lists = Arrays.asList(1,2);
        lists.forEach((i)->{
            try {
                bb.put(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Assert.assertTrue(bb.isFull());
        Assert.assertFalse(bb.isEmpty());
    }

    //12.1.2测试阻塞操作,测试阻塞
    @Test
    public void testTakeBlockWhenEmpty() {
        final BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(2);
        Thread taker = new Thread() {
            public void run() {
                try {
                    int unused = bb.take();
                    System.out.println("没有阻塞,测试失败!");; //如果运行到这里说明有错误
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("阻塞程序已被中断!");
                }
            }
        };

        try {
            taker.start();
            System.out.println("已进入阻塞状态");
            Thread.sleep(5000); //等待5秒后终止中断等待的线程
            taker.interrupt(); //中断阻塞的线程
            taker.join(); //等待线程执行结束
            System.out.println("hello");
            //isAlive()返回false代表taker线程已终止
            Assert.assertFalse(taker.isAlive());
        } catch (InterruptedException e) {
            //e.printStackTrace();
            System.out.println("运行到这里代码测试失败!");
        }
    }
}
