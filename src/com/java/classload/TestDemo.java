package com.java.classload;

/**
 * 这个类编译后的class不能出现在classpath路径下，否则就会调用应用类加载器加载，而不是自定义类加载器
 */
public class TestDemo {

    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        System.out.println("结果是："+ (a + b));
    }
}
