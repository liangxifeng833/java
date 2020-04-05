package com.concurrency.example.guavaCache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

/**
 * Description: myFirst
 * Create by liangxifeng on 19-8-9
 */
public class TestLoadingCache {
    LoadingCache<String,String> cacheBuilder = CacheBuilder
            .newBuilder()
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    String strProValue="hello "+key+"!";
                    return strProValue;
                }
            });
    @Test
    public void TestLoadingCache() throws Exception {
        System.out.println("jerry value:"+cacheBuilder.apply("jerry"));
    }

    @Test
    public void testB() throws ExecutionException {
        cacheBuilder.put("harry", "ssdded");
        System.out.println("harry value:"+cacheBuilder.get("harry"));
    }
}

