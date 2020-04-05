package com.concurrency.book.fireChapter.create_cache;

import java.math.BigInteger;

/**
 * 实现计算,需要很长时间得到计算结果
 * Create by liangxifeng on 19-9-9
 */
class ExpensiveFunction implements Computable<String,BigInteger> {

    @Override
    public BigInteger compute(String arg) throws InterruptedException {
        //计算需要很长时间,这里模拟1秒
        Thread.sleep(1000);
        return new BigInteger(arg);
    }
}
