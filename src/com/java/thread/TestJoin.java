package com.java.thread;

/**
 * 插队
 * join 合并线程，待此线程执行完成后，再执行其他线程，其他线程阻塞
 * join()是成员方法，由Thread 对象调用
 */
public class TestJoin {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i=0; i < 100; i++) {
                System.out.println("lambda..." + i);
            }
        });
        thread.start();

        for (int i = 0; i < 100; i++) {
            if (i % 20 == 0) {
                thread.join();// main 线程礼让
            }
            System.out.println("main..." + i);
        }
    }
}
