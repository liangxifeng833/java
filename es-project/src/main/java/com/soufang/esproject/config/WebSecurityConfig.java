package com.soufang.esproject.config;

import com.soufang.esproject.security.AuthFilter;
import com.soufang.esproject.security.AuthProvider;
import com.soufang.esproject.security.LoginAuthFailHandler;
import com.soufang.esproject.security.LoginUrlEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * Description: es-project
 * Create by liangxifeng on 19-4-20
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * HTTP 权限控制
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class);

        //资源访问权限
        http.authorizeRequests()
                .antMatchers("/admin/login").permitAll() //管理员登录入口
                .antMatchers("/static/**").permitAll() //静态资源
                .antMatchers("user/login").permitAll() //普通用户登录入口
                //.antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/admin/**").permitAll()
                //.antMatchers("/user/**").hasAnyRole("ADMIN","USER")
                .antMatchers("/user/**").permitAll()
                //配置接口页面
                .antMatchers("/api/user/**").hasAnyRole("ADMIN","USER")
                .and()
                .formLogin()
                .loginProcessingUrl("/login") //配置角色登录入口
                .failureHandler(authFailHandler())
                .and()
                .logout()
                .logoutUrl("/logout/page")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(urlEntryPoint())
                .accessDeniedPage("/403");

        //关闭csrf防御
        http.csrf().disable();
        //开启同源配置
        http.headers().frameOptions().sameOrigin();
    }

    /**
     * 自定义认证策略
     */
    @Autowired
    public void configGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider()).eraseCredentials(true);
    }

    @Bean
    public AuthProvider authProvider() {
        return new AuthProvider();
    }

    @Bean
    public LoginUrlEntryPoint urlEntryPoint() {
        return new LoginUrlEntryPoint("/user/login");
    }

    @Bean
    public LoginAuthFailHandler authFailHandler() {
        return new LoginAuthFailHandler(urlEntryPoint());
    }


    @Bean
    public AuthenticationManager authenticationManager() {
        AuthenticationManager authenticationManager = null;
        try {
            authenticationManager =  super.authenticationManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authenticationManager;
    }


    @Bean
    public AuthFilter authFilter() {
        AuthFilter authFilter = new AuthFilter();
        authFilter.setAuthenticationManager(authenticationManager());
        authFilter.setAuthenticationFailureHandler(authFailHandler());
        return authFilter;
    }

}
