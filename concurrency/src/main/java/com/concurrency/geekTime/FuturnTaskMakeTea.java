package com.concurrency.geekTime;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 使用FuturnTask实现泡茶逻辑
 * FutureTask ft1 完成洗水壶、烧开水、泡茶的任务
 * FutureTask ft2 完成洗茶壶、洗茶杯、拿茶叶的任务
 * ft1 这个任务在执行泡茶任务前，需要等待 ft2 把茶叶拿来
 * 所以ft1内部需要引用ft2,并在执行泡茶之前，调用 ft2 的 get() 方法实现等待
 * Create by liangxifeng on 19-8-20
 */
public class FuturnTaskMakeTea {

    /**
     * 执行结果：
     *   T1:洗水壶
         T2:洗茶壶
         T1:烧开水
         T2:洗茶杯
         T2:拿茶叶
         T1等待T2:拿到茶叶:龙井
         T1:泡茶
         T1上茶:龙井
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> ft2 = new FutureTask<>(new T2Task());
        FutureTask<String> ft1 = new FutureTask<>(new T1Task(ft2));

        //线程1执行任务ft1
        Thread t1 = new Thread(ft1);
        t1.start();

        //线程2执行任务f2
        Thread t2 = new Thread(ft2);
        t2.start();
        //等待线程t1执行结果
        System.out.println(ft1.get());


    }
}

// T1Task 需要执行的任务：洗水壶、烧开水、泡茶
class T1Task implements Callable {
    FutureTask<String> ft2;
    T1Task(FutureTask<String> ft2) {
        this.ft2 = ft2;
    }
    @Override
    public Object call() throws Exception {
        System.out.println("T1:洗水壶");
        TimeUnit.SECONDS.sleep(1);

        System.out.println("T1:烧开水");
        TimeUnit.SECONDS.sleep(3);

        //阻塞等待ft2拿到茶叶
        String tea = ft2.get();
        System.out.println("T1等待T2:拿到茶叶:"+tea);


        System.out.println("T1:泡茶");
        TimeUnit.SECONDS.sleep(3);
        return "T1上茶:"+tea;

    }
}


// T2Task 需要执行的任务：洗茶壶、洗茶杯、拿茶叶
class T2Task implements Callable {
    @Override
    public Object call() throws Exception {
        System.out.println("T2:洗茶壶");
        TimeUnit.SECONDS.sleep(1);

        System.out.println("T2:洗茶杯");
        TimeUnit.SECONDS.sleep(2);

        System.out.println("T2:拿茶叶");
        TimeUnit.SECONDS.sleep(1);
        return "龙井";

    }
}