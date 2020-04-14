package com.java.thread.concurrent;


import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时调度
 */
public class TestTimer {
    public static void main(String[] args) {
        MyTask task = new MyTask();
        Timer timer = new Timer();
        // 执行安排
        System.out.println("---start---");
        //timer.schedule(task, 5000); // 5秒后开始任务，执行任务一次
        //timer.schedule(task, 5000, 2000); // 5秒后开始任务，每隔2秒执行任务一次
        Calendar calender = new GregorianCalendar(2020,3,5, 23, 8, 50);
        timer.schedule(task, ((GregorianCalendar) calender).getTime(), 2000); // 设定到某个具体的时间开始任务，每隔2秒执行任务一次

    }
}

class MyTask extends TimerTask {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("广告之后，马上回来");
        }
        System.out.println("---end---");
    }
}
