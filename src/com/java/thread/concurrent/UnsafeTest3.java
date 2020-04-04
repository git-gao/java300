package com.java.thread.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * 线程不安全测试：操作容器
 */
public class UnsafeTest3 {
    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            new Thread(()-> {
                list.add(Thread.currentThread().getName());
            }).start();
        }
        Thread.sleep(10000);
        System.out.println(list.size()); // 不等于10000
    }
}
