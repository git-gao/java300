package com.java.thread.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS: 比较并交换
 * 悲观锁实现方式
 * compare and swap
 */
public class TestCAS {
    // 库存
    private static AtomicInteger stock = new AtomicInteger(5);

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    // 模拟延时
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Integer left = stock.decrementAndGet();
                if (left < 1) {
                    System.out.println("抢完了！");
                    return;
                }
                System.out.println(Thread.currentThread().getName() + " 抢了一件商品");
                System.out.println("---> 还剩" + left);
            }).start();
        }
    }
}
