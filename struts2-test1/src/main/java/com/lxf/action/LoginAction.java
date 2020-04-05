package com.lxf.action;

import com.lxf.po.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 用户登录action
 * @author lxf
 *
 */

public class LoginAction  extends ActionSupport 
implements ModelDriven<User>{
    private User user = new User();
    public String login()
    {
        System.out.println(user.getUsername());
        System.out.println(user.getBookList().get(0));
        System.out.println(user.getBookList().get(1));
        System.out.println(user.getUserList().get(0).getUsername());
        System.out.println(user.getUserList().get(1).getUsername());
        return SUCCESS;
    }
    /**
     * 实现ModelDriven接口的方法
     * @return
     */
    @Override
    public User getModel() {
        // TODO Auto-generated method stub
        return user;
    }
    @Override
    public void validate() {
        if(user.getUsername()==null || "".equals(user.getUsername()))
        {
            this.addFieldError("username", "用户名不能为空!");
        }
    }
    
    
    
}
