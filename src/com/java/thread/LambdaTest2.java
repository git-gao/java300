package com.java.thread;

/**
 * Lambda 表达式两个个参数
 */
public class LambdaTest2 {
    public static void main(String[] args) {
        IInterest interest = (int a, int b) -> {
            System.out.println("i love lambda -->" + (a+b));
            return a + b;
        };
        interest.lambda(1, 2);

        // 简化，省略参数类型
        interest = (a, b) -> {
            System.out.println("i love lambda -->" + (a+b));
            return a + b;
        };
        interest.lambda(1, 1);

        //
        interest = (a, b) -> a + b;
        interest = (a, b) -> 100;

        System.out.println(interest.lambda(1, 3));
    }
}

interface IInterest {
    int lambda(int a, int b);
}

class Interest implements IInterest {

    @Override
    public int lambda(int a, int b) {
        System.out.println("i love lambda -->" + (a+b));
        return a + b;
    }
}
