package com.java.thread;

/**
 * 多线程测试类
 * 创建线程方式二
 * 1. 创建：实现 Runnable + 重写 run()
 * 2. 启动：创建实现类对象 + Thread 对象 + start()
 */
public class TestRunnable implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 2000; i++) {
            System.out.println("一边听歌");
        }
    }

    public static void main(String[] args) {
        // 创建实现类对象
        TestRunnable runnable = new TestRunnable();
        // 创建代理类对象
        Thread thread = new Thread(runnable);
        // 启动
        thread.start();

        // 写法二： 匿名类
        new Thread(new TestRunnable()).start();

        for (int i = 0; i < 2000; i++) {
            System.out.println("一边 coding");
        }

    }
}
