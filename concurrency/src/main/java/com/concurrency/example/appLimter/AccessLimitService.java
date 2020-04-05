package com.concurrency.example.appLimter;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Component;

/**
 * Description: myFirst
 * Create by liangxifeng on 19-8-18
 */
@Component
public class AccessLimitService {

    //每秒只发出5个令牌
    RateLimiter rateLimiter = RateLimiter.create(5.0);

    /**
     * 尝试获取令牌
     * @return
     */
    public boolean tryAcquire(){
        return rateLimiter.tryAcquire();
    }

    public Double acquire() {
        return rateLimiter.acquire();
    }

}
