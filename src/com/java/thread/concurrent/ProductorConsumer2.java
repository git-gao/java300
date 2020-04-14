package com.java.thread.concurrent;

/**
 * 生产者消费者模式实现方式二：信号灯法
 * 借助标志位
 */
public class ProductorConsumer2 {
    public static void main(String[] args) {
        TV tv = new TV();
        new Player(tv).start();
        new Watcher(tv).start();
    }
}

// 生产者 演员
class Player extends Thread {
    TV tv;

    public Player(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                tv.play("新闻联播");
            } else {
                tv.play("新闻1+1");
            }
        }
    }
}
// 消费者 观众
class Watcher extends Thread {
    TV tv;

    public Watcher(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            tv.watch();
        }
    }
}
// 同一个资源 电视
class TV {
    private String voice;
    // 信号灯, true 表示演员表演 观众等待，false 表示观众观看，演员等待
    boolean flag = true;

    // 表演
    public synchronized void play(String voice) {
        // 演员等待
        if (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("播放了:" + voice);
        this.voice = voice;
        // 唤醒
        this.notifyAll();
        // 切换标识
        this.flag = !flag;
    }

    // 观看
    public synchronized void watch() {
        // 观众等待
        if (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("听到了:" + voice);
        // 唤醒
        this.notifyAll();
        // 切换标识
        this.flag = !flag;
    }
}

