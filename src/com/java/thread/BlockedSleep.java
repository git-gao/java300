package com.java.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * sleep() 时间倒计时
 * 线程暂停
 * 线程进入阻塞状态，解除阻塞态后，线程进入运行态
 */
public class BlockedSleep {

    public static void main(String[] args) {
        Date endTime = new Date(System.currentTimeMillis() + 1000*10);
        long endTarget = endTime .getTime() - 10000;

        try {
            while (true) {
                String date = new SimpleDateFormat("HH:mm:ss").format(endTime);
                System.out.println(date);
                Thread.sleep(1000);
                endTime = new Date(endTime.getTime() - 1000);

                if (endTime.getTime() <= endTarget) {
                    break;
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
