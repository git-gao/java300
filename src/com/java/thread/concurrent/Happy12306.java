package com.java.thread.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用 synchronized 同步方法购票
 */
public class Happy12306 {
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
        seats2.add(4);
        seats2.add(5);
        seats2.add(6);
        HappyWeb12306 web12306 = new HappyWeb12306(available, "K888");
        new Passenger(web12306, "小爱", seats1).start();
        new Passenger(web12306, "小度", seats2).start();
    }
}


class Passenger extends Thread {

    List<Integer> seats;

    /**
     * 继承 Thread 作为代理，子类中可以加线程变量
     * 延续父类构造器，同时添加自己的属性
     * @param target
     * @param name
     * @param seats
     */
    public Passenger(Runnable target, String name, List<Integer> seats) {
        super(target, name);
        this.seats = seats;
    }

}

// 火车票网
class HappyWeb12306 implements Runnable{
    List<Integer> available;// 可用位置
    String name; // 名称

    public HappyWeb12306(List<Integer> available, String name) {
        this.available = available;
        this.name = name;
    }

    @Override
    public void run() {
        // 当前线程进行强制转型
        Passenger passenger = (Passenger) Thread.currentThread();
        boolean flag = bookTicket(passenger.seats);
        if (flag) {
            System.out.println("出票成功：" + Thread.currentThread().getName() + "，您的位置为--> " + passenger.seats);
        } else {
            System.out.println("出票失败：" + Thread.currentThread().getName() + "，抱歉，位置不够QAQ");
        }
    }

    /**
     * 判断购票是否成功
     * 使用 synchronized 同步的方法
     * @param seats 预定的位置
     * @return
     */
    public synchronized boolean bookTicket(List<Integer> seats) {
        System.out.println("欢迎乘坐" + this.name +"，当前可用位置为：" + available);
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