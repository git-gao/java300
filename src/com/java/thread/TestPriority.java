package com.java.thread;

/**
 * 线程优先级
 * 1. NORM_PRIORITY 5
 * 2. MIN_PRIORITY 1
 * 3. MAX_PRIORITY 10
 * 不代表绝对的优先顺序
 */
public class TestPriority {
    public static void main(String[] args) {
        MyPriority myPriority = new MyPriority();
        Thread thread1 = new Thread(myPriority, "Thread-1");
        Thread thread2 = new Thread(myPriority, "Thread-2");
        Thread thread3 = new Thread(myPriority, "Thread-3");
        Thread thread4 = new Thread(myPriority, "Thread-4");
        Thread thread5 = new Thread(myPriority, "Thread-5");
        Thread thread6 = new Thread(myPriority, "Thread-6");

        thread1.setPriority(Thread.MIN_PRIORITY);
        thread2.setPriority(Thread.MAX_PRIORITY);
        thread3.setPriority(Thread.MAX_PRIORITY);
        thread4.setPriority(Thread.MIN_PRIORITY);
        thread5.setPriority(Thread.MIN_PRIORITY);
        thread6.setPriority(Thread.MAX_PRIORITY);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
    }
}

class MyPriority implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "-->" + Thread.currentThread().getPriority());
        Thread.yield();
    }
}
