package com.java.thread.lock;

/**
 * 可重入锁
 */
public class TestReentrantLock {

    public void test() {

        // 第一次获得锁
        synchronized(this) {
            while (true) {
                // 第二次获得同样的锁
                synchronized (this) {
                    System.out.println("ReentrantLock!");
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new TestReentrantLock().test();
    }
}
