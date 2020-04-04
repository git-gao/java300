package com.java.thread.concurrent;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 线程安全：操作并发容器
 * 加了可重入锁
 */
public class SynContainer {

    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10000; i++) {
            new Thread(()-> {
                list.add(Thread.currentThread().getName());
            }).start();
        }
        Thread.sleep(1000);// 防止线程未执行完就打印了
        System.out.println(list.size()); // 不等于10000
    }
}
