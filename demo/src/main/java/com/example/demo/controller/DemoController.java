package com.example.demo.controller;

import com.example.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 *  去除 @RequestBody 接参 首尾空格
 * @author liangxifeng
 * @date 2022/7/21 14:38
 */

@RestController
@Slf4j
@RequestMapping("/demo")
public class DemoController {

    @PostMapping("/addTest")
    public void addTest(@RequestBody User user) {
        System.out.println("userName="+ user.getUserName()+" length="+user.getUserName().length());
        System.out.println("passwrod="+ user.getPassword()+" length="+user.getPassword().length());
    }

    @PostMapping("/addTest2")
    public void demo2(@RequestParam String name) {
        System.out.println("name="+ name+" length="+ name.length());
    }

    @GetMapping("/get/{name}")
    public void demo3(@PathVariable("name") String name) {
        System.out.println("name=" + name + " length = " + name.length());
    }


}
