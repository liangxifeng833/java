package com.example.demo;

import org.junit.Test;

import java.util.regex.Pattern;

/**
 * @author liangxifeng
 * @date 2022/7/12 14:05
 */

public class TestDemo {
    @Test
    public void testPattern() {
        String content = "I am noob " +
                "from runoob.com.";

        //String pattern = ".*runoob.*";
        //String pattern = "^[\d+]$";
        //String pattern = "^[1-9]*$";
        //匹配整数要不没有，如果有则>0
        String pattern = "^(\\s?)|(\\+?[1-9][0-9]*)$";

        boolean isMatch = Pattern.matches(pattern, "10");
        System.out.println("字符串中是否包含了 'runoob' 子字符串? " + isMatch);
    }

}
