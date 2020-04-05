package com.lxf.action;
/**
 * 用户登录页面，用来判断用户是否登录
 */

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class LoginNewAction extends ActionSupport implements SessionAware{
    private String username;
    private String password;
    private Map<String,Object> session;
    /**
     * 实现SessionAware中的接口
     */
    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
        // TODO Auto-generated method stub
    }
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;   
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * 处理登录请求
     */
    public String loginNew()    
    {
        if("admin".equals(username) && "123".equals(password))
        {
            session.put("loginInfo", username);
            return SUCCESS;
        }else
        {
            session.put("loginError", "用户名或密码不正确!");
            return ERROR;
        }
    }
}
