package com.soufang.esproject.web.controller;

import com.soufang.esproject.base.ApiResponse;
import com.soufang.esproject.base.LoginUserUtil;
import com.soufang.esproject.entity.Role;
import com.soufang.esproject.service.ISmsService;
import com.soufang.esproject.service.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 瓦力.
 */
@Controller
public class HomeController {

    @Autowired
    private ISmsService smsService;

    @GetMapping(value = {"/","/index"})
    public String index(Model model){
        model.addAttribute("name","你好我是从controller赋值过来的,ok");
        return "index";
    }
    @GetMapping("get")
    @ResponseBody
    public ApiResponse get()
    {
        System.out.println("I am get request.....");
        //return new ApiResponse(11,"dafdsa",null);
        return ApiResponse.ofMessage(2,"数据库未查询到任何记录");
        //return ApiResponse.ofStatus(ApiResponse.Status.BAD_REQUEST);
        //return ApiResponse.ofMessage(101010,"hahahah");
    }
    @PostMapping("post")
    @ResponseBody
    public ApiResponse post(@RequestParam(name = "id") int id, @RequestParam(name = "name") String name)
    {
        System.out.println("I am post request.....");
        System.out.println("id = " +id);
        System.out.println("name = " +name);
        return ApiResponse.ofMessage(100,"hello world!");
    }
    @PutMapping("put")
    @ResponseBody
    public ApiResponse put(@RequestBody  Role role)
    {
        for(int i=0; i<10000000; i++){
            System.out.println(i);
        }
        System.out.println("I am post request.....");
        System.out.println("role  = " +role);
        return ApiResponse.ofMessage(100,"hello world!");
    }

    /*
    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        return "index";
    }*/

    @GetMapping("/404")
    public String notFoundPage() {
        return "404";
    }

    @GetMapping("/403")
    public String accessError() {
        return "403";
    }

    @GetMapping("/500")
    public String internalError() {
        return "500";
    }

    @GetMapping("/logout")
    public String logoutPage() {
        return "logout";
    }


    /**
     * 发送短信验证码api
     * @param telephone
     * @return
     */
    @GetMapping(value = "sms/code")
    @ResponseBody
    public ApiResponse smsCode(@RequestParam("telephone") String telephone) {

        if (!LoginUserUtil.checkTelephone(telephone)) {
            return ApiResponse.ofMessage(HttpStatus.BAD_REQUEST.value(), "请输入正确的手机号");
        }
        ServiceResult<String> result = smsService.sendSms(telephone);
        if (result.isSuccess()) {
            return ApiResponse.ofSuccess("");
        } else {
            return ApiResponse.ofMessage(HttpStatus.BAD_REQUEST.value(), result.getMessage());
        }

    }
}
