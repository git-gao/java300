package com.java.thread;

/**
 * Lambda 表达式一个参数
 */
public class LambdaTest1 {

    public static void main(String[] args) {
        ILove love = (int a) -> {
            System.out.println("i love lambda -->" + a);
        };
        love.lambda(100);

        // 简化,省略类型
        love = (a) -> {
            System.out.println("i love lambda -->" + a);
        };
        love.lambda(50);

        // 只有一个参数，省略括号
        love = a -> {
            System.out.println("i love lambda -->" + a);
        };
        love.lambda(20);

        // 只有一行代码，可以省略花括号{}
        love = a -> System.out.println("i love lambda -->" + a);
        love.lambda(10);
    }
}

interface ILove {
    void lambda(int a);
}

class Love implements ILove {

    @Override
    public void lambda(int a) {
        System.out.println("i love lambda -->" + a);
    }
}
