package com.java.thread;

/**
 * yield 礼让线程
 * 让当前线程执行暂停而不是阻塞线程，线程由运行态进入就绪态
 */
public class TestYield1 {

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i=0; i < 100; i++) {
                System.out.println("lambda..." + i);
            }
        }).start();

        for (int i = 0; i < 100; i++) {
            if (i % 20 == 0) {
                Thread.yield();// main 线程礼让
            }
            System.out.println("main..." + i);
        }
    }
}

