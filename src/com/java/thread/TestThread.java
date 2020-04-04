package com.java.thread;

/**
 * 创建线程方式一
 *
 * 1. 创建：继承 Thread + 重写 run()
 * 2. 启动：创建子类对象 + start()
 */
public class TestThread extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 2000; i++) {
            System.out.println("一边听歌");
        }
    }
    public static void main(String[] args) {
        // 创建子类对象
        TestThread thread = new TestThread();
        // 启动
        thread.start();

        for (int i = 0; i < 2000; i++) {
            System.out.println("一边coding");
        }
    }

}
