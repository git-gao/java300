package com.java.annotation.orm;

import com.java.annotation.MyAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 注解的测试
 */
public class StuTest {

    public static void main(String[] args) throws Exception {
        try {
            Class clazz = Class.forName("com.java.annotation.orm.Student");
            // 获得类的所有注解
            Annotation[] annotation = clazz.getAnnotations();
            for (Annotation a: annotation) {
                System.out.println("获取类的注解：" + a);
            }

            // 获得类的指定注解
            StuTable stuTable = (StuTable) clazz.getAnnotation(StuTable.class);
            MyAnnotation myAnnotation = (MyAnnotation) clazz.getAnnotation(MyAnnotation.class);
            System.out.println(stuTable.value());
            System.out.println(myAnnotation.testName());

            // 获得类的属性的注解
            Field f = clazz.getDeclaredField("age");
            // 获取属性名上的注解
            StuField stuField = f.getAnnotation(StuField.class);
            System.out.println("属性的注解" + stuField.columnName() + ", " + stuField.type() + ", " + stuField.length());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
