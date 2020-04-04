package com.java.thread;

/**
 *
 */
public class LambdaThread {

    // 静态内部类
    static class Test implements Runnable{
        public void run() {
            for (int i = 0; i < 20; i++) {
                System.out.println("一边听歌");
            }
        }
    }

    public static void main(String[] args) {
        Runnable thread = new Thread();
        // 1. 静态内部类
        new Thread(new Test()).start();

        // 2. 局部内部类，放到方法里
        class Test2 implements Runnable{
            public void run() {
                for (int i = 0; i < 20; i++) {
                    System.out.println("一边听歌");
                }
            }
        }
        new Thread(new Test2()).start();

        // 3. 匿名内部类
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 20; i++) {
                    System.out.println("一边听歌");
                }
            }
        }).start();

        // Lambda 表达式，接口只能有一个方法
        new Thread(()->{
            // 只关注线程体
            for (int i = 0; i < 20; i++) {
                System.out.println("一边听歌");
            }
        }).start();

    }
}
