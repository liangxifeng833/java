package com.concurrency.example.guavaCache;

import com.google.common.cache.*;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Description: myFirst
 * Create by liangxifeng on 19-8-13
 */
@Slf4j
public class GuavaCachDemo2 {
    private Cache<String, Man> cache;
    private LoadingCache<String,Man> loadingCache;
    private RemovalListener<String, Man> removalListener;

    public void Init(){
        //移除key-value监听器
        removalListener = new RemovalListener<String, Man>(){
            public void onRemoval(RemovalNotification<String, Man> notification) {
                log.info(notification.getKey()+"被移除");
                //可以在监听器中获取key,value,和删除原因
                notification.getValue();
                notification.getCause();//EXPLICIT、REPLACED、COLLECTED、EXPIRED、SIZE

            }};
        //可以使用RemovalListeners.asynchronous方法将移除监听器设为异步方法
        //removalListener = RemovalListeners.asynchronous(removalListener, new ThreadPoolExecutor(1,1,1000, TimeUnit.MINUTES,new ArrayBlockingQueue<Runnable>(1)));
    }
    //loadingCache
    public void InitLoadingCache() {
        //指定一个如果数据不存在获取数据的方法
        CacheLoader<String, Man> cacheLoader = new CacheLoader<String, Man>() {
            @Override
            public Man load(String key) throws Exception {
                //模拟mysql操作
                log.info("LoadingCache测试 从mysql加载缓存ing...(2s)");
                Thread.sleep(2000);
                log.info("LoadingCache测试 从mysql加载缓存成功");
                Man tmpman = new Man();
                tmpman.setId(key);
                tmpman.setName("其他人");
                if (key.equals("001")) {
                    tmpman.setName("张三");
                    return tmpman;
                }
                if (key.equals("002")) {
                    tmpman.setName("李四");
                    return tmpman;
                }
                return tmpman;
            }
        };
        //缓存数量为1，为了展示缓存删除效果
        loadingCache = CacheBuilder.newBuilder().
                //设置2分钟没有获取将会移除数据
                        expireAfterAccess(2, TimeUnit.MINUTES).
                //设置2分钟没有更新数据则会移除数据
                        expireAfterWrite(2, TimeUnit.MINUTES).
                //每1分钟刷新数据
                        refreshAfterWrite(1,TimeUnit.MINUTES).
                //设置key为弱引用
                        weakKeys().
//                weakValues().//设置存在时间和刷新时间后不能再次设置
//                softValues().//设置存在时间和刷新时间后不能再次设置
        maximumSize(1).
                        removalListener(removalListener).
                        build(cacheLoader);
    }

    //获取数据，如果不存在返回null
    public Man getIfPresentloadingCache(String key){
        return loadingCache.getIfPresent(key);
    }

    //获取数据，如果数据不存在则通过cacheLoader获取数据，缓存并返回
    public Man getCacheKeyloadingCache(String key){
        try {
            return loadingCache.get(key);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    //直接向缓存put数据
    public void putloadingCache(String key,Man value){
        log.info("put key :{} value : {}",key,value.getName());
        loadingCache.put(key,value);
    }

    public void InitDefault() {
        cache = CacheBuilder.newBuilder().
                expireAfterAccess(2, TimeUnit.MINUTES).
                expireAfterWrite(2, TimeUnit.MINUTES).
//                refreshAfterWrite(1,TimeUnit.MINUTES).//没有cacheLoader的cache不能设置刷新，因为没有指定获取数据的方式
        weakKeys().
//                weakValues().//设置存在时间和刷新时间后不能再次设置
//                softValues().//设置存在时间和刷新时间后不能再次设置
        maximumSize(1).
                        removalListener(removalListener).
                        build();
    }

    public Man getIfPresentCache(String key){
        return cache.getIfPresent(key);
    }
    public Man getCacheKeyCache(final String key) throws ExecutionException {
        return cache.get(key, new Callable<Man>() {
            public Man call() throws Exception {
                //模拟mysql操作
                log.info("Cache测试 从mysql加载缓存ing...(2s)");
                Thread.sleep(2000);
                log.info("Cache测试 从mysql加载缓存成功");
                Man tmpman = new Man();
                tmpman.setId(key);
                tmpman.setName("其他人");
                if (key.equals("001")) {
                    tmpman.setName("张三");
                    return tmpman;
                }
                if (key.equals("002")) {
                    tmpman.setName("李四");
                    return tmpman;
                }
                return tmpman;
            }
        });
    }

    public void putCache(String key,Man value){
        log.info("put key :{} value : {}",key,value.getName());
        cache.put(key,value);
    }
}
