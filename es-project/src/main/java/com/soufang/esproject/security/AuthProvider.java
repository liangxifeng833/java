package com.soufang.esproject.security;

import com.soufang.esproject.entity.User;
import com.soufang.esproject.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * 自定义认证实现,专门存放安全认证罗辑
 * Description: es-project
 * Create by liangxifeng on 19-4-20
 */
@Component
public class AuthProvider implements AuthenticationProvider {
    @Autowired
    private IUserService userServcie;
    private final Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //获取输入的用户名
       String userName = authentication.getName();
       //获取输入的密码
       String inputPassword = (String)authentication.getCredentials();

       //从数据库中读取用户名
       User  user = userServcie.findUserByName(userName);
       if( user == null ){
           throw new AuthenticationCredentialsNotFoundException("authError");
       }
       //验证数据库库中的密码和用户输入的密码是否一致
       if( this.passwordEncoder.isPasswordValid(user.getPassword(),inputPassword,user.getId() ) ){
           return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
       }
        throw new BadCredentialsException("authError");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
