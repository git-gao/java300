package com.java.thread.concurrent;

/**
 * 电影院购票
 */
public class HappyCinema {
    public static void main(String[] args) {
        Cinema cinema = new Cinema(20, "万达影院");
        new Thread(new Customer(cinema, 1), "小爱").start();
        new Thread(new Customer(cinema, 2), "小度").start();
    }
}

class Customer implements Runnable {

    Cinema cinema;
    int seats;

    public Customer(Cinema cinema, int seats) {
        this.cinema = cinema;
        this.seats = seats;
    }

    @Override
    public void run() {
        synchronized (cinema) {
            boolean flag = cinema.bookTicket(seats);
            if (flag) {
                System.out.println("出票成功：" + Thread.currentThread().getName() + "-->位置为：" + seats);
            } else {
                System.out.println("出票失败：" + Thread.currentThread().getName() + "-->位置不够");
            }
        }
    }
}

// 影院
class Cinema {
    int available;// 可用位置
    String name; // 名称

    public Cinema(int available, String name) {
        this.available = available;
        this.name = name;
    }

    public boolean bookTicket(int seats) {
        System.out.println("可用位置为：" + available);
        if (seats > available) {
            return false;
        }

        available -= seats;
        return true;
    }
}
