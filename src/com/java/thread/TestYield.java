package com.java.thread;

/**
 * yield 礼让线程
 * yield() 是静态方法
 * 线程直接进入就绪态
 */
public class TestYield {

    public static void main(String[] args) {
        new Thread(new MyYield(), "aa").start();
        new Thread(new MyYield(), "bb").start();
    }
}

class MyYield implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "---> start");
        Thread.yield();
        System.out.println(Thread.currentThread().getName() + "---> end");
    }
}
