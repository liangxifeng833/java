package com.concurrency.example.publish;

import com.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * Description: 错误的发布对象
 * Create by liangxifeng on 19-7-18
 */
@Slf4j
@NotThreadSafe
public class UnsafePublish {
    private String[] states = {"a","b","c"};

    public String[] getStates() {
        return states;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        //输出 [a, b, c]
        log.info("{}", Arrays.toString(unsafePublish.getStates()));

        /**
         * 通过UnsafePublish公有方法获取UnsafePublish内部私有变量states的引用
         * 并修改该引用的值,导致其他线程在通过同样的方式获取该私有变量states的时候,已经被其他线程修改过了
         * 导致线程不安全
         */
        unsafePublish.getStates()[0] = "d";
        //输出 [d, b, c],证明私有变量已被外部程序修改
        log.info("{}", Arrays.toString(unsafePublish.getStates()));
    }
}
