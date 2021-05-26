package com.java.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 线程测试
 */
public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {
        Long start = System.currentTimeMillis();
        Random random = new Random();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    list.add(random.nextInt());
                }
            };
            thread.start();
            thread.join();
        }
        Long end = System.currentTimeMillis();
        System.out.println("耗时: " + (end - start));
        System.out.println("大小: " + list.size());
    }
}
