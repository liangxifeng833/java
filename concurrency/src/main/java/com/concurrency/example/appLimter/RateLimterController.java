package com.concurrency.example.appLimter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description: Guava的RateLimiter限流测试
 * Create by liangxifeng on 19-8-18
 */
@Controller
@RequestMapping("guavalimitdemo")
public class RateLimterController {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private AccessLimitService accessLimitService;

    @RequestMapping("/access")
    @ResponseBody
    public String access(){
                //尝试获取令牌
        Double await = accessLimitService.acquire();
//            //模拟业务执行500毫秒
//            try {
//                Thread.sleep(2000);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
//            return "aceess success [" + sdf.format(new Date()) + "],awaittime="+await;



        //尝试获取令牌
        if(accessLimitService.tryAcquire()){
            //模拟业务执行500毫秒
            try {
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return "aceess success [" + sdf.format(new Date()) + "]";
        }else{
            return "aceess limit [" + sdf.format(new Date()) + "]";
        }
    }

}
