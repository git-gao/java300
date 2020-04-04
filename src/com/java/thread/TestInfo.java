package com.java.thread;

/**
 * 其他方法
 * isAlive():表示线程是否还活着
 * setName()、getName(): 代理名称
 * currentThread(): 当前线程
 */
public class TestInfo {
    public static void main(String[] args) throws InterruptedException {

        System.out.println(Thread.currentThread().getName() + " is live " + Thread.currentThread().isAlive());
        // 设置名字：真是角色 + 代理名字
        MyInfo info = new MyInfo("战斗机");
        Thread thread = new Thread(info);
        System.out.println(thread.getName() + " is live " + thread.isAlive());
        thread.setName("公鸡");// 改变线程名字
        thread.start();
        Thread.sleep(1000);
        System.out.println(thread.getName() + " is live " + thread.isAlive());
    }
}

class MyInfo implements Runnable {

    private String name;

    public MyInfo(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "--->" + name);
    }
}
