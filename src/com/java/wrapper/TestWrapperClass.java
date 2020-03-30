package com.java.wrapper;

/**
 * 包装类
 */
public class TestWrapperClass {

    public static void main(String[] args) {

        // 自动装箱和自动拆箱
        Integer i = 100; // 自动装箱，Integer i = Integer.valueOf(100);
        int j = i;       // 自动拆箱, int j = i.intValue();

        // 包装类型的比较
        Integer integer1 = new Integer(1024);
        Integer integer2 = new Integer(1024);
        Integer integer5 = 1024;
        Integer integer6 = 1024;
        System.out.println(integer1 == integer2); //false
        System.out.println(integer1.equals(integer2)); // true
        System.out.println(integer5 == integer6); // false

        // 系统初始化时就创建了[-128 - 127] 之间的缓存数组
        // 当调用 valueOf 方法时，首先检查数字是否在[-128 - 127] 之间，如果在这个范围，则直接从缓存数组中获取创建好的对象
        // 如果不在这个范围，则创建新的 Integer 对象
        Integer integer3 = 127;
        Integer integer4 = 127;
        System.out.println(integer3 == integer4); // true

        // 基本数据类型转化为包装类型
        Integer a = new Integer(1);
        Integer b = Integer.valueOf(2);

        // 包装类型转化为基本数据类型
        int c = a.intValue();
        Long d = b.longValue();
        System.out.println(c); // 1
        System.out.println(d); // 2

        // 字符转转化为包装类
        Integer e = new Integer("123");
        Integer f = Integer.parseInt("456");
        System.out.println(e);
        System.out.println(f);

        // 包装类转化为字符串
        String s = e.toString();
        System.out.println(s); // 123
    }

}
