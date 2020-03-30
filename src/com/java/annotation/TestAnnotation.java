package com.java.annotation;

import java.util.Date;

/**
 * @SuppressWarnings 警告
 * SuppressWarnings(value = "all") 关闭 SuppressWarnings 所有警告
 */
@SuppressWarnings(value = "all")
public class TestAnnotation {

    @Override
    public String toString() {
        return "@Override";
    }

    /**
     * @Deprecated 修饰的方法，为不建议使用此方法
     */
    @Deprecated
    public static void aaa() {
        System.out.println("aaa");
    }

    /**
     * 警告
     */
    @SuppressWarnings(value = {"unchecked", "deprecation"})
    public void bbb() {

    }

    public static void main(String[] args) {
        aaa(); // 此时提示未使用
        Date date = new Date();
        date.parse("date");
    }
}

class Father {
    public void test() {

    }

}

class son extends Father{

    /**
     * Override 检查子类重写父类方法是否正确
     */
    @Override
    public void test() {
        System.out.println("注解测试");
    }
}
