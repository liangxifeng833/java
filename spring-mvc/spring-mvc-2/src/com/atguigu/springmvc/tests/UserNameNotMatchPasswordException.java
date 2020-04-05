package com.atguigu.springmvc.tests;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * 自定义异常类
 * @author lxf
 *
 */
@ResponseStatus(value=HttpStatus.FORBIDDEN, reason=";用户名和密码不匹配")
public class UserNameNotMatchPasswordException extends RuntimeException {
    private static final long serialVersionUID = 1L;
}