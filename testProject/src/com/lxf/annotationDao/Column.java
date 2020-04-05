package com.lxf.annotationDao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 实体类成员字段注解
 * @author lxf
 *
 */

//作用域是字段
@Target({ElementType.FIELD})
//声明周期为运行时
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
	String value();
}
