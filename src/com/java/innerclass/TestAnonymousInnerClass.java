package com.java.innerclass;

/**
 * 测试匿名内部类
 */
public class TestAnonymousInnerClass {

    public static void test(Inner inner) {
        System.out.println("###########");
        inner.show();
    }

    //psvm sout+TAB
    public static void main(String[] args) {
        TestAnonymousInnerClass.test(new Inner() {
            @Override
            public void show() {
                System.out.println("TestAnonymousInnerClass");
            }
        });
    }
}

// 写在这里或单独定义都可以
/*interface Inner {
    void show();
}*/
