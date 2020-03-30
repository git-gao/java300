package com.java.classload;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 * 类加载的全过程
 *
 * 加载 -- 验证 -- 准备 -- 解析 -- 初始化 -- 使用 -- 卸载
 *         |                 |
 *         ————————链接———————
 *
 * 类加载时，先加载静态变量和静态代码块
 * 初始化一个类时，先初始化其父类
 *
 * 初始化阶段是执行类构造器方法<clinit>的过程
 */
public class TestClassLoad {

    static {
        System.out.println("静态初始化类TestClassLoad");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("TestClassLoad 类的 main 方法");
        // 主动调用
        A a = new A(); // new 一个类的对象，类开始初始化
        System.out.println(A.width); // 使用类的静态成员和静态方法,类开始初始化
        //Class.forName("com.java.classload.A");// 反射调用也会发生类的初始化
        //A a2 = new A();//  类加载初始化只进行一次

        // 被动调用
        //A[] B = new A[2]; // 通过数组定义类引用，不会发生类的初始化
        //System.out.println(A.height); // 使用final 常量，不会引起类的初始化
        //System.out.println(B.width); // 子类引用父类的静态变量，不会导致子类的初始化
    }
}

class A  extends A_Father {

    public A() {
        System.out.println("创建A类对象");
    }

    public static final int height = 200;

    // 静态变量
    public static int width = 100;

    // 静态代码块
    static {
        System.out.println("静态初始化类A");
        //int width = 300;
        width = 300;
    }

}

class B extends A {
    static {
        System.out.println("静态初始化类B");
    }
}

class A_Father {
    static {
        System.out.println("静态初始化类 A_Father");
    }
    public A_Father() {
        System.out.println("创建A_Father对象");
    }
}
