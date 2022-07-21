package com.example.demo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author liangxifeng
 * @date 2022/7/21 14:40
 */

@Data
@Accessors(chain = true)
public class User {
    private Integer id;
    private String userName;
    private String password;
}
