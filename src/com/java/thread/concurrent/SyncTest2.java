package com.java.thread.concurrent;

/**
 * synchronized 锁定同步块
 */
public class SyncTest2 {

    public static void main(String[] args) {
        Account1 account = new Account1("结婚礼金", 200);
        SyncDrawing you = new SyncDrawing(account, 80, "可悲的你");
        SyncDrawing wife = new SyncDrawing(account, 90, "幸福的她");
        you.start();
        wife.start();
    }
}


class Account1 {

    public String name; // 名字
    public int money; // 金额

    public Account1(String name, int money) {
        this.name = name;
        this.money = money;
    }
}

class SyncDrawing extends Thread {

    Account1 account; // 账户
    int drawingMoney;//取的钱数
    int drawingTotal;//取的总数

    public SyncDrawing(Account1 account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    @Override
    public void run() {
        test();
    }

    /**
     * synchronized 锁定方法，但是锁定的这个资源对象 this 不对，使用这个 test() 方法是 SyncDrawing 的对象，
     * 应该锁定的是操作对象 Account
     */
    public void test() {
        synchronized (account) {
            if (account.money - drawingMoney < 0) {
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
}