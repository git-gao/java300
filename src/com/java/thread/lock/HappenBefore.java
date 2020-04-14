package com.java.thread.lock;

/**
 * 指令重排
 * 写后读、写后写、读后写
 */
public class HappenBefore {
    // 变量1
    private static int a = 0;
    // 变量2
    private static boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            // 线程1 读取数据
            Thread t1 = new Thread(() -> {
                a = 1;
                flag = true;
            });

            // 线程2 更改数据
            Thread t2 = new Thread(() -> {
                if (flag) {
                    a *= 1;
                }

                if (a == 0) {
                    System.out.println("happne before --> " + a); // 指令重排发生：happne before --> 1
                }
            });

            t1.start();
            t2.start();

            // 合并线程
            t1.join();
            t2.join();
        }
    }
}
