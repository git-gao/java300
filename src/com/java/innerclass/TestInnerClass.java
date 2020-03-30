package com.java.innerclass;

/**
 * 测试非静态内部类
 */
public class TestInnerClass {
    public static void main(String[] args) {
        // 外部类创建内部类对象
        Outer1.Inner inner = new Outer1(). new Inner();
        inner.show();

        Outer1 outer1 = new Outer1();
        Outer1.Inner inner1= outer1.new Inner();
        inner1.show();
    }
}

class Outer1 {

    // 外部类的成员变量
    private int age = 20;

    // 外部类的成员方法
    private void testOuter() {
        // 外部类的非静态方法可以使用非静态内部类的非静态变量、非静态方法
        Inner inner = new Inner(); // 外部类中定义内部类
        System.out.println("外部类的成员方法");
        System.out.println(inner.age);
        inner.print();
    }



    // 非静态内部类必须寄存在一个外部类对象里
    class Inner {

        int age = 18;
        // 非静态内部类不能有静态方法、属性和静态初始块
        //static int a = 18;

        public void show() {
            int age = 30;
            System.out.println("外部类的成员变量age：" + Outer1.this.age);// 可以直接访问外部类的成员
            System.out.println("内部类的成员变量age：" + this.age);
            System.out.println("内部类的局部变量age：" + age);
            testOuter();
        }

        public  void print() {
            System.out.println("内部类的非静态方法");
        }
    }
}
