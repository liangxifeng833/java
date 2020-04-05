package com.concurrency.book.fireChapter.create_cache;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 使用FutrueTask作为缓存map的value
 * 当调用compute方法时,判断如果cache中没有该值则新简FutrueTask
 * 然后将新建立的FutrueTask,put到cache中,接下来通过FutrueTask.run计算耗时任务
 * 最后通过FutrueTask.get()获取值,如果FutrueTask.run计算未完成,
 * 则get方法阻塞等待一直等到有计算结果返回
 *
 * Create by liangxifeng on 19-9-9
 */
public class CreateCacheDemo2<A,V> implements Computable<A,V> {
    private final Map<A,Future<V>> cache = new ConcurrentHashMap<A,Future<V>>();
    private final Computable<A,V> c;

    CreateCacheDemo2(Computable<A,V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        Future<V> f = cache.get(arg);
        if (f == null) {
            Callable<V> eval = new Callable<V>() {
                @Override
                public V call() throws Exception {
                    return c.compute(arg);
                }
            };
            FutureTask<V> ft = new FutureTask<V>(eval);
            f = ft;
            cache.putIfAbsent(arg,ft);//如果没有key=arg则put
            ft.run(); //这里调用c.compute
        }
        V res = null;
        try {
             res = f.get();
        } catch (ExecutionException e) {
            System.out.println("异常类型="+e.getCause());
            e.printStackTrace();
        }
        return res;
    }
}
