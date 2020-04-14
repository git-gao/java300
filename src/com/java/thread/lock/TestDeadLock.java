package com.java.thread.lock;

/**
 * 死锁：过多的同步导致相互不释放资源,从而造成相互等待，一般发生于同步中持有多个对象的锁
 *
 * 避免：不要在同一个同步代码块中。同时持有多个对象的锁
 * 这里模拟死锁，线程A获得了 Lipstic 对象的锁，在没有释放 Lipstic 锁的情况下想要去获取 Mirror 锁，这时线程B已经获得了 Mirror 锁，
 * 在释放 Mirror 锁之前，必须先获得 Lipstic 锁，因此闭环发生，陷入死锁循环
 */
public class TestDeadLock {

    public static void main(String[] args) {
        Makeup girl1 = new Makeup(0, "Tina");
        Makeup girl2 = new Makeup(2, "Lisa");
        Makeup girl3 = new Makeup(1, "Bane");
        girl1.start();
        girl2.start();
        girl3.start();
    }
}

// 口红
class Lipstic {

}

// 镜子
class Mirror {

}

// 化妆
class Makeup extends Thread {
    static Lipstic lipstic = new Lipstic();
    static Mirror mirror = new Mirror();
    // 选择
    private int choice;
    // 名字
    private String girl;

    public Makeup(int choice, String girl) {
        this.choice = choice;
        this.girl = girl;
    }

    @Override
    public void run() {
        makeup();
    }

    public void makeup() {
        System.out.println("lock test start...");
        if (choice == 0) {
            // 获取口红的锁
            synchronized (lipstic) {
                System.out.println(girl + "获得口红");
                try {
                    // 一秒后想获得镜子，给一点让另一个线程锁住 mirror 对象
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 获取镜子的锁
                synchronized (mirror) {
                    System.out.println(girl + "照镜子");
                }
            }

        } else {
            // 获取镜子的锁
            synchronized (mirror) {
                System.out.println(girl + "照镜子");
                try {
                    // 一秒后想获得镜子
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 获取口红的锁
                synchronized (lipstic) {
                    System.out.println(girl + "获得口红");
                }
            }

        }
    }
}
