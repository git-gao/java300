package com.java.thread;

/**
 * 默认：线程都是用户线程，jvm 等待用户线程执行完毕才会停止
 * 守护线程：是为用户线程服务的；jvm 停止不用等待守护线程执行完毕
 *
 */
public class TestDaemon {
    public static void main(String[] args) {

        Thread thread1 = new Thread(new Youg());
        Thread thread2 = new Thread(new God());
        thread1.start();
        // 将用户线程调整为守护线程,当 thread1 执行完毕后，jvm 不用等待 thread2 执行完毕就会停止
        thread2.setDaemon(true);
        thread2.start();
    }
}

class Youg extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 10*100; i++) {
            System.out.println("happy life..." + i);
        }
        System.out.println("0000000");
    }
}

class God extends Thread {

    @Override
    public void run() {
        while (true) {
            System.out.println("God bless you...");
        }
    }
}


