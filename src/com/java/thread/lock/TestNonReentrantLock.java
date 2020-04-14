package com.java.thread.lock;

/**
 * 不可重入锁：锁不可以延续使用
 */
public class TestNonReentrantLock {

    Lock lock = new Lock();

    public void aVoid () {
        lock.lock();
        doSomething();
        lock.unLock();
    }

    public void doSomething () {
        lock.lock();
        System.out.println("Non ReentrantLock!");
        lock.unLock();
    }

    public static void main(String[] args) {
        TestNonReentrantLock nonReentrantLock = new TestNonReentrantLock();
        nonReentrantLock.aVoid();
        nonReentrantLock.doSomething();

    }
}

class Lock {

    // 是否占用
    private boolean isLocked = false;
    // 使用锁
    public synchronized void lock() {
        while (isLocked) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        isLocked = true;
    }

    // 释放锁
    public synchronized void unLock () {
        isLocked = false;
        notify();
    }
}
