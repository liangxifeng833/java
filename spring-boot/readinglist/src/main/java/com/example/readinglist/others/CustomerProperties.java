package com.example.readinglist.others;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *  自定义属性
 */
@Component
//带有lxf-customer前缀的属性，在application.yml中定义
@ConfigurationProperties("lxf-customer")
public class CustomerProperties {
    private String username;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
