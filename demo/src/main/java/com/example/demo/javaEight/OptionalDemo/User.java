package com.example.demo.javaEight.OptionalDemo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author liangxifeng
 * @date 2022/7/12 10:46
 */

@Data
@Accessors(chain = true)
public class User {
    private String name;
}
