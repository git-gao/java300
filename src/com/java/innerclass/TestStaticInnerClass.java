package com.java.innerclass;

/**
 * 测试静态内部类
 */
public class TestStaticInnerClass {
    public static void main(String[] args) {
        Outer2.Inner2 inner = new Outer2.Inner2();
    }
}

class Outer2 {

    static class Inner2 {

    }
}
