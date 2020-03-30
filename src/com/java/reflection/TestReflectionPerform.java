package com.java.reflection;

import java.lang.reflect.Method;

/**
 * 测试反射的性能问题
 *
 * 反射调用方法的效率大概是普通调用的 1/4
 * 反射跳过安全检查，效率能稍微提高一些
 */
public class TestReflectionPerform {

    public static void test1() {
        User user = new User();

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000000L; i++) {
            user.setName("test1");
        }
        System.out.println(user.getName());
        long endTime = System.currentTimeMillis();
        System.out.println("普通方法调用, 执行10亿次，耗时" + (endTime - startTime) + "ms");
    }

    public static void test2() throws Exception{
        User user = new User();
        Class clazz = user.getClass();

        Method method = clazz.getDeclaredMethod("setName", String.class);
        // method.setAccessible(true); // 不添加禁止访问检查

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000000L; i++) {
            method.invoke(user, "test2");
        }
        System.out.println(user.getName());
        long endTime = System.currentTimeMillis();
        System.out.println("反射动态调用方法, 执行10亿次，耗时" + (endTime - startTime) + "ms");

    }

    public static void test3() throws Exception{
        User user = new User();
        Class clazz = user.getClass();

        Method method = clazz.getDeclaredMethod("setName", String.class);
        method.setAccessible(true); // 跳过安全检查

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000000L; i++) {
            method.invoke(user, "test3");
        }
        System.out.println(user.getName());
        long endTime = System.currentTimeMillis();
        System.out.println("反射动态调用方法，跳过安全检查, 执行10亿次，耗时" + (endTime - startTime) + "ms");
    }

    public static void main(String[] args) throws Exception{
        test1();
        test2();
        test3();
    }
 }
