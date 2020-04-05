package com.concurrency.geekTime;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 利用CompletionService实现询价系统
 * 模拟三个异步线程分别向三个询价系统询价
 * 最后将询价结果异步保存到数据库中
 * Create by liangxifeng on 19-8-20
 */
public class CompletionServiceAskPrice {
    public static void main(String[] args) throws Exception {
        //自己通过阻塞队列的方式实现询价系统
        //CompletionServiceAskPrice.selfAskPrice();

        //使用ComplationService方式实现询价系统
        //CompletionServiceAskPrice.complationServiceAsk();

        //询价后比较最小值
        CompletionServiceAskPrice.askPriceCompareMin();
    }

    /**
     * 自己通过阻塞队列的方式实现询价时间长短的问题
     * 执行结果：
     异步保存报价到数据库:价格=2
     异步保存报价到数据库:价格=1
     异步保存报价到数据库:价格=3
     * @throws Exception
     */
    private static void selfAskPrice() throws Exception {
        //创建线程
        ExecutorService executor = Executors.newFixedThreadPool(3);

        //异步向电商S1询价
        Future<Integer> f1 = executor.submit(()->{
            try {
                //模拟向S1询价
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });

        //异步向电商S2询价
        Future<Integer> f2 = executor.submit(()->{
            try {
                //模拟向S2询价
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 2;
        });


        //异步向电商S3询价
        Future<Integer> f3 = executor.submit(()->{
            try {
                //模拟向S3询价
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 3;
        });

        //创建阻塞队列实现
        BlockingDeque<Integer> bq = new LinkedBlockingDeque<>();
        // 电商 S1 报价异步进入阻塞队列
        executor.execute(()->{
            try {
                bq.put(f1.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

        // 电商 S2 报价异步进入阻塞队列
        executor.execute(()->{
            try {
                bq.put(f2.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        // 电商 S3 报价异步进入阻塞队列
        executor.execute(()->{
            try {
                bq.put(f3.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

        // 异步保存所有报价
        for (int i=0;i<3; i++) {
            Integer r = bq.take();
            executor.execute(()-> System.out.println("异步保存报价到数据库,价格="+r));
        }
    }

    /**
     * 使用ComplationService方式实现询价系统
     * CompletionService 接口提供的 submit()方法提交了三个询价操作，这三个询价操作将会被
     * CompletionService 异步执行。最后，我们通过CompletionService 接口提供的 take()获取一个 Future 对象
     * 调用 Future 对象的 get() 方法就能返回询价操作结果了
     执行结果：
         异步保存报价到数据库:价格=2
         异步保存报价到数据库:价格=1
         异步保存报价到数据库:价格=3
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private static void complationServiceAsk() throws InterruptedException, ExecutionException {

        //创建线程
        ExecutorService executor = Executors.newFixedThreadPool(3);
        CompletionService<Integer> cs = new ExecutorCompletionService<>(executor);

        //异步向电商S1询价
        //任务执行结果的 Future 对象就是加入到 completionQueue的阻塞队列中
        cs.submit(()->{
            try {
                //模拟向S1询价
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });

        //异步向电商S2询价
        cs.submit(()->{
            try {
                //模拟向S2询价
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 2;
        });


        //异步向电商S3询价
        cs.submit(()->{
            try {
                //模拟向S3询价
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 3;
        });

        // 将询价结果异步保存数据
        for (int i=0; i<3; i++) {
            Integer r = cs.take().get();
            executor.execute(()-> System.out.println("异步保存报价到数据库:价格="+r));
        }
    }

    /**
     * 询价后比较最小值
     * 输出结果：
        异步保存报价到数据库:价格=2
        异步保存报价到数据库:价格=1
        异步保存报价到数据库:价格=3
        三个询价系统寻到的价格最小值=1
     */
    private static void askPriceCompareMin() throws InterruptedException, ExecutionException {
        //创建线程
        ExecutorService executor = Executors.newFixedThreadPool(3);
        CompletionService<Integer> cs = new ExecutorCompletionService<>(executor);

        //异步向电商S1询价
        //任务执行结果的 Future 对象就是加入到 completionQueue的阻塞队列中
        cs.submit(()->{
            try {
                //模拟向S1询价
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });

        //异步向电商S2询价
        cs.submit(()->{
            try {
                //模拟向S2询价
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 2;
        });


        //异步向电商S3询价
        cs.submit(()->{
            try {
                //模拟向S3询价
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 3;
        });

        AtomicReference<Integer> m = new AtomicReference<>(Integer.MAX_VALUE);
        CountDownLatch countDownLatch = new CountDownLatch(3);
        // 将询价结果异步保存数据
        for (int i=0; i<3; i++) {
            executor.execute(()->{
                Integer r = null;
                try {
                    r = cs.take().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                System.out.println("异步保存报价到数据库:价格="+r);
                m.set(Integer.min(m.get(), r));
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println("三个询价系统寻到的价格最小值="+m.get());
    }

}
