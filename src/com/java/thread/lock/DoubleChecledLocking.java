package com.java.thread.lock;

/**
 * DCI 单例模式：懒汉式基础上，在多线程环境下，对外存在一个对象
 * 1. 提供私有的静态属性 --> 存储对象地址
 * 2. 构造器私有化 --> 避免外部 new 构造器
 * 3. 提供公共的静态方法 --> 获取属性
 */
public class DoubleChecledLocking {

    // 1. 提供私有的静态属性
    // 没有 volatile 其他线程可能访问一个没有初始化的对象
    private static volatile DoubleChecledLocking instance; // 懒汉式

    // 2. 构造器私有化
    private DoubleChecledLocking() {

    }

    /**
     * 3. 提供公共的静态方法
     * 线程 A 进来， instance = null, 创建对象，由于创建对象的延时，线程B进来时对象还未创建完，线程B也创建了对象，这样就存在两个实例
     * @return
     */
    public static DoubleChecledLocking getInstance() {
        // 检测，如果已经存在对象，避免不必要的同步
        if (null != instance) {
            return instance;
        }

        // 同步,成员方法锁定 this ,静态方法锁定类.class
        synchronized (DoubleChecledLocking.class) {
            if (null == instance) {
                try {
                    Thread.sleep(1000); // 模拟对象创建的延时
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                instance = new DoubleChecledLocking();
                // 创建一个对象 1. 开辟空间 2. 初始化对象信息 3. 返回对象的地址引用
            }
        }
        return instance;
    }

    /**
     * 不同步情况测试
     * @return
     */
    public static DoubleChecledLocking getInstance1() {

        // 检测，如果已经存在对象，避免不必要的同步
        if (null != instance) {
            return instance;
        }

        if (null == instance) {
            try {
                Thread.sleep(1000); // 模拟对象创建的延时
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new DoubleChecledLocking();
        }
        return instance;
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(()-> {
            System.out.println(Thread.currentThread().getName() + "线程-->" + DoubleChecledLocking.getInstance());
        });
        Thread t2 = new Thread(()-> {
            System.out.println(Thread.currentThread().getName() + "线程-->" + DoubleChecledLocking.getInstance1());
        });
        t1.start();
        t2.start();
        System.out.println(Thread.currentThread().getName() + "线程-->" + DoubleChecledLocking.getInstance());
    }
}
