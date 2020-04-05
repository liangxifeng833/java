package com.lxf.annotationDao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 实体类类注解
 * @author lxf
 *
 */

//作用域是类或接口
@Target({ElementType.TYPE})
//声明周期为运行时
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
	String value();
}
