package domain.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志测试controller
 * @author  liangxifeng 2018-02-07
 */
@RestController
@RequestMapping(value = "logTest")
@Slf4j
public class LogTestController {
    @GetMapping
    public String testLog(){
        //直接使用log对象打印日志
        log.info("info log....");
        log.debug("debug log....");
        log.error("error log....");
        return "log test success !...";

    }
}
