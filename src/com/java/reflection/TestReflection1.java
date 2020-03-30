package com.java.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 通过反射调用类的方法
 */
public class TestReflection1 {
    public static void main(String[] args) {
        String path = "com.java.reflection.User";
        try {
            Class<User> clazz = (Class<User>)Class.forName(path);
            // 通过反射 API 调用构造方法，构造对象
            User user = clazz.newInstance(); // 调用 User 的构造方法
            System.out.println(user);

            Constructor<User> constructor = clazz.getDeclaredConstructor(int.class, String.class, int.class);
            System.out.println(constructor);
            User user1 = constructor.newInstance(1, "GAO", 18);
            System.out.println(user1.getName());

            // 通过反射 API 调用普通方法
            User user2 = clazz.newInstance();
            System.out.println(user2.getName());
            Method method = clazz.getDeclaredMethod("setName", String.class);
            method.invoke(user2, "testG");
            System.out.println(user2.getName());

            // 通过反射 API 获取属性
            User user3 = clazz.newInstance();
            Field field = clazz.getDeclaredField("name");
            // 不能直接设置私有属性，设置属性不需要做安全检查
            field.setAccessible(true);// setAccessible 设置必须在 set 方法前
            field.set(user3, "GAO3");// 通过反射设置属性
            System.out.println(user3.getName());// 通过类来访问
            System.out.println(field.get(user3));// 通过反射来访问属性

            // 通过反射直接修改公有属性
            User user4 = clazz.newInstance();
            Method method1 = clazz.getDeclaredMethod("setSex", String.class);
            method1.invoke(user4, "男");
            System.out.println(user4.getSex());
            Field field1 = clazz.getDeclaredField("sex");
            field1.set(user4, "女"); // public 属性可直接进行设置
            System.out.println(field1.get(user4));
            System.out.println(user4.getSex());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
