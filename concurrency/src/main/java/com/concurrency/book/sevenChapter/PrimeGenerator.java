package com.concurrency.book.sevenChapter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用volatile类型的域来保存取消状态
 * Create by liangxifeng on 19-9-16
 */
public class PrimeGenerator implements Runnable{
    private  final List<BigInteger> primeList = new ArrayList<>();
    //取消标识
    private volatile boolean cancelled;
    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!cancelled) {
            p = p.nextProbablePrime();
            synchronized (this) {
                primeList.add(p);
            }
        }
    }

    /**
     * 取消任务
     */
    public void cancel() {
        cancelled = true;
    }

    /**
     * 返回list数据，注意这里新new了list为线程安全
     * @return
     */
    public synchronized List<BigInteger> getList() {
        return new ArrayList<BigInteger>(primeList);
    }

    public static void main(String[] args)  {

        //任务执行１秒后终止
        PrimeGenerator generator = new PrimeGenerator();
        new Thread(generator).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //终止任务
            generator.cancel();
        }
        //获取任务执行结果
        System.out.println(generator.getList());
    }
}
