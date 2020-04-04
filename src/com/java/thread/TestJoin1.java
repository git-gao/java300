package com.java.thread;

public class TestJoin1 {
    public static void main(String[] args) {
        System.out.println("爸爸和儿子买烟的故事。。。");
        //new Thread(new Father()).start();
        Thread thread = new Thread(new Father());
        thread.start();
        try {
            // 待 father 线程执行完再执行 main 剩下线程
            thread.join();// join() 方法写在哪个线程，哪个线程就被阻塞，main 线程被阻塞
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("买烟的故事结束了");
    }
}

class Father extends Thread {
    @Override
    public void run() {
        System.out.println("爸爸想买烟");
        System.out.println("让儿子去买包中华");
        Thread thread = new Thread(new Son());
        thread.start();
        try {
            // 待 son 线程执行完再执行 father 剩下线程
            thread.join();// join() 方法写在哪个线程，哪个线程就被阻塞，father 线程被阻塞
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("孩子走丢了，老爸找孩子去了。。。");
        }

        System.out.println("老爸接过烟，把零钱给了儿子");
    }
}

class Son extends Thread {
    @Override
    public void run() {
        System.out.println("儿子接过老爸的钱出门了。。。");
        System.out.println("去游戏厅玩了十秒");
        try {
            for (int i = 1; i <= 10; i++) {
                System.out.println(i + "秒过去了");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("儿子赶紧买烟去");
        System.out.println("手拿一包中华烟");
    }
}
