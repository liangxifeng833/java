package com.lxf.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


//@RestController
@Controller
@RequestMapping("/home")
public class IndexController {
    private static Logger logger = Logger.getLogger(IndexController.class);
    @ModelAttribute
    public void isLogin(HttpSession session) throws Exception {
        if (session.getAttribute("user") == null) {
            throw new Exception("没有权限------1212");
        }
    }
    @RequestMapping("/index")
    public String index() {
        System.out.println("hahahah");
        return "index";
    }
}
