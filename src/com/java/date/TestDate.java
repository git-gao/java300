package com.java.date;

import java.util.Date;

/**
 * 日期测试
 */
public class TestDate {
    public static void main(String[] args) {
        Date date1 = new Date();
        System.out.println(date1);

        // 获取毫秒数
        System.out.println(date1.getTime());

        Date date2 = new Date();
        System.out.println(date2.getTime());
        System.out.println(date2.before(date1));
        System.out.println(date2.after(date1));

        Date date3 = new Date(2020-1900, 4, 6, 22, 10, 12); // 用法不建议使用
        System.out.println(date3);
    }
}
