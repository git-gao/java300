package com.java.thread;

/**
 * 自定义 Lambda 表达式
 */
public class LambdaTest {

    static class Like2 implements ILike {
        @Override
        public void lambda() {
            System.out.println("i like lambda test2.");
        }
    }

    public static void main(String[] args) {
        // 1.外部类
        ILike like = new Like();
        like.lambda();

        // 2. 静态内部类
        like = new Like2();
        like.lambda();

        // 3. 方法内部类
        class Like3 implements ILike {
            public void lambda() {
                System.out.println("i like lambda test3.");
            }
        }

        like = new Like3();
        like.lambda();

        // 4. 匿名内部类
        like = new ILike() {
            @Override
            public void lambda() {
                System.out.println("i like lambda test4.");
            }
        };
        like.lambda();

        // 5. Lambda 表达式
        like = ()-> {
            System.out.println("i like lambda test5.");
        };
        like.lambda();
    }
}

interface ILike {
    void lambda();
}

// 外部类
class Like implements ILike {

    @Override
    public void lambda() {
        System.out.println("i like lambda.");
    }
}
