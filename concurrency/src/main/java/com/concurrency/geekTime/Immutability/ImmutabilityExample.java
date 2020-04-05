package com.concurrency.geekTime.Immutability;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 28.Immutability利用不变性解决并发问题
 * Create by liangxifeng on 19-8-22
 */
public class ImmutabilityExample {
    //库存类
    class WMRange {
        final int upper;
        final int lower;
        WMRange(int upper,int lower) {
            this.upper = upper;
            this.lower = lower;
        }
    }
    private final AtomicReference<WMRange> rf = new AtomicReference<>(new WMRange(0,0));
    //设置库存上限
    public void setUpper(int v) {
        while (true) {
            System.out.println("--");
            //先获取原库存对象(从共享变量中获取)
            WMRange oldR = rf.get();
            if (v < oldR.lower) {
                throw new IllegalArgumentException();
            }
            WMRange newR = new WMRange(v,oldR.lower);
            //CAS设置库存上限
            if (rf.compareAndSet(oldR,newR)) {
                return;
            }
        }
    }

    public WMRange get() {
        return rf.get();
    }

    public static void main(String[] args) {
        ImmutabilityExample example = new ImmutabilityExample();
        example.setUpper(10);
        System.out.println("新设置的库存上限="+example.get().upper);
    }
}
