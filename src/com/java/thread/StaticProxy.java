package com.java.thread;

/**
 * 静态代理
 * 公共接口
 * 1. 真实角色
 * 2. 代理角色
 */
public class StaticProxy {
    public static void main(String[] args) {
        new WeddingMarryCompany(new You()).happyMarry();
        //new Thread().start();
    }
}

interface Marry{
    void happyMarry();
}

class You implements Marry{

    @Override
    public void happyMarry() {
        System.out.println("you and change 终于奔月了...");
    }
}

class WeddingMarryCompany implements Marry{

    private Marry target;

    public WeddingMarryCompany(Marry target) {
        this.target = target;
    }

    @Override
    public void happyMarry() {
        ready();
        this.target.happyMarry();
        after();
    }

    private void ready() {
        System.out.println("布置猪窝");
    }

    private void after() {
        System.out.println("闹玉兔");
    }
}


