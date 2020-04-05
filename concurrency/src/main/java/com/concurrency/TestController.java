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
    public String test() {
      return "test";
    }
}
