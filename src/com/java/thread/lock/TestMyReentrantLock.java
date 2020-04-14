package com.java.thread.lock;


/**
 * 自定义可重入锁
 * 计数器
 */
public class TestMyReentrantLock extends Thread{

    ReLock reLock = new ReLock();

    public void aVoid () {
        reLock.lock();
        System.out.println("锁的计数：" + reLock.getHoldCount());
        doSomething();
        reLock.unLock();
        System.out.println("锁的计数：" + reLock.getHoldCount());
    }

    public void doSomething () {
        reLock.lock();
        System.out.println("锁的计数：" + reLock.getHoldCount());
        System.out.println("My ReentrantLock!");
        reLock.unLock();
        System.out.println("锁的计数：" + reLock.getHoldCount());
    }

    public static void main(String[] args) {
        TestMyReentrantLock reentrantLock = new TestMyReentrantLock();
        reentrantLock.aVoid();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("锁的计数：" + reentrantLock.reLock.getHoldCount());

    }
}

class ReLock {

    // 是否占用
    private boolean isLocked = false;
    // 存储线程
    private Thread lockedBy = null;
    // 锁的使用计数
    private int holdCount = 0;

    // 使用锁
    public synchronized void lock() {
        // 当前线程
        Thread currentThread = Thread.currentThread();
        System.out.println("当前线程：" + currentThread.getName());
        // 如果锁住，并且锁的持有者不是当前线程，那么就开始等待
        while (isLocked && lockedBy != currentThread) {
            try {
                wait();
                System.out.println("该方法已经被锁住，请等待");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        isLocked = true; // 锁住
        lockedBy = currentThread; // 锁的持有者为当前线程
        holdCount++; // 计数加1
    }

    // 释放锁
    public synchronized void unLock () {
        Thread currentThread = Thread.currentThread();
        if (lockedBy == currentThread) {
            holdCount--;
            // 当计数为 0 时
            if (holdCount == 0) {
                isLocked = false; // 释放锁
                notify();
                System.out.println("该方法正在被解锁");
                lockedBy = null; // 锁的持有者为 null
            }
        }
    }

    public int getHoldCount () {
        return holdCount;
    }
}
