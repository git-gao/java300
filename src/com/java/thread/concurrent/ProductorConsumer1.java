package com.java.thread.concurrent;

/**
 * 生产者消费者模式实现方式一：管程法
 * 借助容器
 */
public class ProductorConsumer1 {

    public static void main(String[] args) {
        // 创建一个缓冲区
        SynContainer1 container = new SynContainer1();
        new Productor1(container).start();
        new Consumer1(container).start();
    }
}

// 生产者
class Productor1 extends Thread {
    SynContainer1 container;

    public Productor1(SynContainer1 container) {
        this.container = container;
    }

    @Override
    public void run() {
        // 生产
        for (int i = 0; i < 100; i++) {
            System.out.println("生产--> 第 " + (i+1) + " 个馒头");
            container.push(new Steamebun(i+1));
        }
    }
}
// 消费者
class Consumer1 extends Thread {
    SynContainer1 container;

    public Consumer1(SynContainer1 container) {
        this.container = container;
    }

    @Override
    public void run() {
        // 消费
        for (int i = 0; i < 100; i++) {
            System.out.println("消费--> 第 " + container.pop().id + " 个馒头");
        }
    }
}
// 缓存区
class SynContainer1 {
    Steamebun[] buns = new Steamebun[10]; // 存储容器
    int count = 0; // 计数器

    // 生产
    public synchronized void push(Steamebun bun) {

        // 何时生产：空间存在则进行生产
        // 不能生产，只能等待
        if (count == buns.length) {
            try {
                this.wait(); // 线程阻塞，消费者通知生产停止
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 存在空间可以生产
        buns[count] = bun;
        count++;
        // 存在数据，可以通知消费者
        this.notifyAll();
    }

    // 消费
    public synchronized Steamebun pop() {
        // 消费：容器中存在数据
        // 容器中没有数据则等待
        if (count == 0) {
            try {
                this.wait(); // 线程阻塞，生产者通知消费停止
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 存在数据可以消费
        count--;
        Steamebun bun = buns[count];
        // 存在空间，可以通知生产者
        this.notifyAll();
        return bun;
    }

}
// 模拟数据
class Steamebun {
    int id;

    public Steamebun(int id) {
        this.id = id;
    }
}
