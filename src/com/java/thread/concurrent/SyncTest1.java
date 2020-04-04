package com.java.thread.concurrent;

/**
 * synchronized 锁定方法块
 */
public class SyncTest1 {

    public static void main(String[] args) {
        SyncWeb12306 web12306 = new SyncWeb12306();
        new Thread(web12306, "张三").start();
        new Thread(web12306, "李四").start();
        new Thread(web12306, "王五").start();
    }
}

class SyncWeb12306 implements Runnable {

    private int ticketNums = 10;
    private Boolean flag = true;
    @Override
    public void run() {
        while (flag) {
            test4();
        }
    }

    // synchronized 方法，线程安全，同步，锁定对象的资源
    public synchronized void test() {

        if (ticketNums <= 0 ) {
            flag = false;
            return;
        }

        // 模拟延时
        try {
            Thread.sleep(200);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "-->" + ticketNums--);
    }

    // synchronized 块，线程安全，锁范围太大，影响性能
    public void test1() {
        synchronized(this) {
            if (ticketNums <= 0) {
                flag = false;
                return;
            }

            // 模拟延时
            try {
                Thread.sleep(200);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "-->" + ticketNums--);
        }
    }

    // 锁定对象不对，ticketNums 是可变对象，锁不住
    public void test2() {
        synchronized((Integer)this.ticketNums) {
            if (ticketNums <= 0) {
                flag = false;
                return;
            }

            // 模拟延时
            try {
                Thread.sleep(200);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "-->" + ticketNums--);
        }
    }

    // 锁定范围太小，锁不住
    public void test3() {
        synchronized(this) {
            if (ticketNums <= 0) {
                flag = false;
                return;
            }
        }

        // 模拟延时
        try {
            Thread.sleep(200);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "-->" + ticketNums--);
    }

    // 尽可能锁定合理的范围（不是指代码，是指数据的完整性）
    // double checking
    public void test4() {

        // 考虑没票的情况
        if (ticketNums <= 0) {
            flag = false;
            return;
        }

        synchronized(this){
            // 考虑最后一张票的情况
            if (ticketNums <= 0) {
                flag = false;
                return;
            }
            // 模拟延时
            try {
                Thread.sleep(200);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "-->" + ticketNums--);
        }
    }
}
