package com.lxf.controller;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;

public class BaseController {
    @ModelAttribute
    public void isLogin(HttpSession session) throws Exception {
        if (session.getAttribute("user") == null) {
            throw new Exception("没有权限");
        }
    }
}
