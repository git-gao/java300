package com.java.annotation.orm;

import com.java.annotation.MyAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ORM 思想，对象关系映射
 * 数据库表：tb_student
 * 字段：id     long    10  主键
 *       name   String  20
 *       age    int     3
 */
@StuTable("tb_student")
@MyAnnotation
public class Student {

    @StuField(columnName = "id", type = "long", length = 10)
    private long id;

    @StuField(columnName = "name", type = "String", length = 20)
    private String name;

    @StuField(columnName = "age", type = "int", length = 3)
    private int age;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
