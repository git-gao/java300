package com.java.annotation;

/**
 * 自定义注解的测试
 */

/**
 * @Target(value = {ElementType.METHOD, ElementType.TYPE})
 * 表示该注解既可以用于方法，也可以用于类
 */
@MyAnnotation
public class TestMyAnnotation {

    /**
     * @Target(value = ElementType.METHOD) 表示该注解只能用于方法
     */
    @MyAnnotation(id = 1)
    public void testTarget() {

    }
}
