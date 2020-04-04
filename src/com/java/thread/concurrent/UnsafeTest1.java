package com.java.thread.concurrent;

/**
 * 线程不安全测试：买票
 * 数据有负数、数据有重复
 *
 */
public class UnsafeTest1 {
    public static void main(String[] args) {
        Web12306 web12306 = new Web12306();
        new Thread(web12306, "张三").start();
        new Thread(web12306, "李四").start();
        new Thread(web12306, "王五").start();
    }

}

class Web12306 implements Runnable {

    private int ticketNums = 10;
    private Boolean flag = true;
    @Override
    public void run() {
        while (flag) {
            test();
        }
    }

    public void test() {
        if (ticketNums < 0 ) {
            flag = false;
            return;
        }

        // 模拟延时
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "-->" + ticketNums--);
    }
}
