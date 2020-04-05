package com.concurrency.geekTime;

import java.util.Date;
import java.util.concurrent.*;

/**
 * 让我们通过一个简单的需求来使用下 Fork／Join 框架，需求是：计算 1+2+3+4 的结果。
   使用 Fork／Join 框架首先要考虑到的是如何分割任务，如果我们希望每个子任务最多执行两个数的相加，那么我们设置分割的阈值是 2
   由于是 4 个数字相加，所以 Fork／Join 框架会把这个任务 fork成两个子任务，
   子任务一负责计算 1+2，子任务二负责计算 3+4，
   然后再 join 两个子任务的结果。
   因为是有结果的任务，所以必须继承 RecursiveTask，实现代码如下：
 * Create by liangxifeng on 19-8-21
 */
public class ForkJoinExample extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 2; //阀值
    private int start;
    private int end;

    public ForkJoinExample(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        //如果任务足够小就计算任务
        boolean canCompute = (end-start) <= THRESHOLD;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            //如果任务>阀值，就分裂成两个子任务计算
            int middle =  (start+end)/2;
            ForkJoinExample leftTask = new ForkJoinExample(start,middle);
            ForkJoinExample rightTask = new ForkJoinExample(middle+1,end);
            //异步执行子任务
            leftTask.fork();
            rightTask.fork();
            //等待子任务完成并得到结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();
            //合并子任务
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * 使用fork/join方式实现累加操作
         */
        //创建分治任务线程池
        ForkJoinPool fjp = new ForkJoinPool(4);
        long startTime = new Date().getTime();
        //生成一个计算任务，负责计算1+2+3+4
        ForkJoinExample task = new ForkJoinExample(1,4);

        //启动分治任务
        Future<Integer> result = fjp.submit(task);
        //输出计算结果
        System.out.println(result.get()); //输出：10
        long endTime = new Date().getTime();
        System.out.println("forkJoin并行计算累加用时："+ (endTime-startTime) +"毫秒");

        /**
         * 使用普通的单线程累加方式实现累加操作
         * 通过这里对比，也没有发现使用多任务fork/join
         * 比单线程节省时间，反而比单线的方式花费的时间还要多
         * fork/join 20毫秒，单线程：0毫秒 (1~10000累加)
         */
        long startTime1 = new Date().getTime();
        int res = task.add(1,4);
        System.out.println("普通单线程计算累加结果="+res);
        long endTime1 = new Date().getTime();
        System.out.println("普通单线程计算累加用时："+ (endTime1-startTime1) +"毫秒");

    }

    public int add(int start,int end){
        int sum = 0;
        for(int i=start; i<=end; i++) {
           sum += i;
        }
        return sum;
    }

}

