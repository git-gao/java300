package com.java.date;

import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 测试日期类
 */
public class TestCalendar {
    public static void main(String[] args) {
        Calendar calendar = new GregorianCalendar(2999, 10, 9, 10, 5, 6);
        System.out.println(calendar);
        // 年
        int year = calendar.get(Calendar.YEAR);
        System.out.println(year);

        // 月 0-11 表示对应的月份
        int month = calendar.get(Calendar.MONTH);
        System.out.println(month);

        // 日
        int day = calendar.get(Calendar.DATE);
        System.out.println(day);

        // 星期 1-7， 1 表示周天
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println(week);

        // 设置日期的相关元素 set
        Calendar calendar1 = new GregorianCalendar();
        calendar1.set(Calendar.YEAR, 2020);
        calendar1.set(Calendar.MONTH, 01);
        System.out.println(calendar1);

        // 日期的计算 add
        Calendar calendar2 = new GregorianCalendar();
        calendar2.add(Calendar.YEAR, -100);
        System.out.println(calendar2);

        // 日期对象和时间对象的转换
        Date date = new Date();
        Calendar calendar3 = new GregorianCalendar();
        calendar3.setTime(date);
        System.out.println(calendar3);

        printCalender(calendar3);

    }

    public static void printCalender(Calendar calendar) {
        //打印：2020年10月1日 10时10分10秒 周三
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int dayWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        String dayWeek1 = dayWeek == 0 ? "日": dayWeek + "";

        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        System.out.println(year + "年" + month + "月" + day + "日" + " " + hour + "时" + minute + "分" + second + "秒" + " " + "周" + dayWeek1);

    }
}
