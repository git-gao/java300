package com.java.thread.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * 电影院购票改进版（可选位置）
 */
public class HappyCinema2 {
    public static void main(String[] args) {
        List<Integer> available = new ArrayList<>();
        available.add(1);
        available.add(2);
        available.add(3);
        available.add(4);
        available.add(5);
        available.add(6);
        available.add(7);
        List<Integer> seats1 = new ArrayList<>();
        seats1.add(1);
        seats1.add(2);
        List<Integer> seats2 = new ArrayList<>();
        seats2.add(1);
        seats2.add(4);
        seats2.add(5);
        seats2.add(6);
        Cinema2 cinema = new Cinema2(available, "万地影院");
        new Thread(new Customer2(cinema, seats1), "小爱").start();
        new Thread(new Customer2(cinema, seats2), "小度").start();
    }
}

class Customer2 implements Runnable {

    Cinema2 cinema;
    List<Integer> seats;

    public Customer2(Cinema2 cinema, List<Integer> seats) {
        this.cinema = cinema;
        this.seats = seats;
    }

    @Override
    public void run() {
        // 这里使用 synchronized 同步块，锁定的是电影院对象，应该放到 Cinema2 类中
        synchronized (cinema) {
            boolean flag = cinema.bookTicket(seats);
            if (flag) {
                System.out.println("出票成功：" + Thread.currentThread().getName() + "，您的位置为--> " + seats);
            } else {
                System.out.println("出票失败：" + Thread.currentThread().getName() + "，抱歉，位置不够QAQ");
            }
        }
    }
}

// 影院
class Cinema2 {
    List<Integer> available;// 可用位置
    String name; // 名称

    public Cinema2(List<Integer> available, String name) {
        this.available = available;
        this.name = name;
    }

    /**
     * 判断购票是否成功
     * @param seats 预定的位置
     * @return
     */
    public boolean bookTicket(List<Integer> seats) {
        System.out.println("欢迎光临" + this.name +"，当前可用位置为：" + available);
        List<Integer> newAvailable = new ArrayList<>();
        // 拷贝一份出来进行操作
        newAvailable.addAll(available);
        // 从已有的位置中，减去 seats 中选定的位置
        newAvailable.removeAll(seats);
        // 判断是否相等
        if(available.size() - newAvailable.size() != seats.size()) {
            return false;
        }
        // 成功
        available = newAvailable;

        return true;
    }
}