package com.java.thread.concurrent;

import java.util.ArrayList;
import java.util.List;

public class SyncTest3 {
    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            new Thread(()-> {
                synchronized (list) {
                    list.add(Thread.currentThread().getName());
                }
            }).start();
        }
        Thread.sleep(1000);// 防止线程未执行完就打印了
        System.out.println(list.size()); // 不等于10000
    }
}
