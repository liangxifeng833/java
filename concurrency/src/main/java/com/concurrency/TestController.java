package com.concurrency;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description: myFirst
 * Create by liangxifeng on 19-7-16
 */
@Controller
@Slf4j
class TestController {

    @RequestMapping("/test")
    @ResponseBody
    public synchronized String test() throws InterruptedException {
        System.out.println("随机数="+System.currentTimeMillis());
        Thread.sleep(6000000);
        return "test";
    }
    @RequestMapping("/test2")
    @ResponseBody
    public synchronized String test2() throws InterruptedException {
        System.out.println("随机数="+System.currentTimeMillis());
        return "test2";
    }

}
