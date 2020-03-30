package com.java.annotation.orm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * student 表的注解
 * Target {ElementType.FIELD} 开启对字段注解
 */
@Target(value = {ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface StuTable {

    // 一个参数时就用 value
    String value();
}
