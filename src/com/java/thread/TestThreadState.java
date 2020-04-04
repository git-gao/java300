package com.java.thread;


/**
 * 观察线程所有状态
 */
public class TestThreadState {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("...");
            }
        });

        // NEW 一个对象时
        Thread.State state = thread.getState();//NEW
        System.out.println(state);
        // 启动线程时
        thread.start();
        state = thread.getState();//RUNNABLE
        System.out.println(state);

        // 线程阻塞
        /*while (state != Thread.State.TERMINATED) {
            state = thread.getState(); // TIMED_WAITING, BLOCKED
            System.out.println(state);
        }*/

        while (true) {
            int num = Thread.activeCount();
            System.out.println(num);
            if (num == 1) {
                break;
            }
            Thread.sleep(200);
            state = thread.getState();
            System.out.println(state);
        }

        // 线程死亡
        //state = thread.getState();
       // System.out.println(state);

    }
}
