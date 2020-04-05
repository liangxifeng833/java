package com.concurrency.annoations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，该注解负责标注［线程安全］的类或写法
 * Create by liangxifeng on 19-7-16
 */
//作用在类上
@Target(ElementType.TYPE)
//只是一个标注，　编译的时候会被忽略
@Retention(RetentionPolicy.SOURCE)
public @interface ThreadSafe {
    String value() default "";
}
