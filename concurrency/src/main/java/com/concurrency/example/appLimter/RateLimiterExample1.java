package com.concurrency.example.appLimter;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * Description: myFirst
 * Create by liangxifeng on 19-8-18
 */
@Slf4j
public class RateLimiterExample1 {
    //每秒钟放入5个令牌，相当于每秒只允许执行5个请求
    public static final RateLimiter RATE_LIMITER = RateLimiter.create(5);

    public static void main(String[] args) {
        RateLimiter limiter = RateLimiter.create(10);

        for(int i = 1; i < 10; i++ ) {
            if (limiter.tryAcquire() ) {
                System.out.println("cutTime=" + System.currentTimeMillis() + " acq:" + i + " waitTime:");
            }
            //double waitTime = limiter.acquire();
            System.out.println("cutTime=" + System.currentTimeMillis() + " acq:" + i + " waitTime:");
        }

//        for (int i = 0; i < 10; i++) {
//            RATE_LIMITER.acquire();
//            handle(i);
//            // 尝试从令牌桶中获取令牌，若获取不到则等待300毫秒看能不能获取到
//            if (RATE_LIMITER.tryAcquire(300, TimeUnit.MILLISECONDS)) {
//                // 获取成功，执行相应逻辑
//                handle(i);
//            }
//        }
    }
    private static void handle(int i) {
        log.info("{}", i);
    }
}
