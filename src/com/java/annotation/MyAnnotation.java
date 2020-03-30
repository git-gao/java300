package com.java.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 * 元注解： @Target
 *          @Retention 描述注解的生命周期
 */
@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME) // RetentionPolicy.RUNTIME 可以被反射读取到
public @interface MyAnnotation {
    /**
     * 参数类型： String
     * 参数名：testName
     * 默认值：default 给空值
     * @return
     */
    String testName() default "test1";

    int id() default 1;
}
