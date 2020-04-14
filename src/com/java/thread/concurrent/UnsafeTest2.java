package com.java.thread.concurrent;

/**
 * 线程不安全测试：取钱
 */
public class UnsafeTest2 {

    public static void main(String[] args) {
        Account account = new Account("结婚礼金", 100);
        Drawing you = new Drawing(account, 80, "可悲的你");
        Drawing wife = new Drawing(account, 90, "幸福的她");
        you.start();
        wife.start();
    }
}

class Account {

    public String name; // 名字
    public int money; // 金额

    public Account(String name, int money) {
        this.name = name;
        this.money = money;
    }
}

class Drawing extends Thread {

    Account account; // 账户
    int drawingMoney;// 取的钱数
    int drawingTotal;// 取的总数

    public Drawing(Account account, int drawingMoney, String name) {
        super(name);// 继承 Thread 可以调用父类方法，传入线程名称
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    @Override
    public void run() {
        if (account.money - drawingTotal < 0) {
            return;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        account.money -= drawingMoney;
        drawingTotal += drawingMoney;
        System.out.println(this.getName() + "-----> 账户余额为：" + account.money);
        System.out.println(this.getName() + "-----> 口袋的钱：" + drawingTotal);
    }
}