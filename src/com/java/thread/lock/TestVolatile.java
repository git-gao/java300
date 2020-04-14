package com.java.thread.lock;

/**
 * volatile 只用于保证数据的同步，也就是可见性，不保证原子性
 * 轻量的 synchronize
 */
public class TestVolatile {

    private volatile static int num = 0;
    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            while (num == 0) {

            }
        }).start();

        Thread.sleep(1000);
        num = 1;
        System.out.println(num); // 等于 1 的时候循环应该停止的
    }
}
