package com.java.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 测试时间对象和字符串之间相互转换
 * DateFormat 抽象类和 SimpleDateFormat 实现类
 */
public class TestDateFormat {
    public static void main(String[] args) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        // 将时间转化为字符串
        String str = dateFormat.format(new Date());
        System.out.println(str);

        // 将字符串按照 "格式字符串指定的格式" 转成相应的时间对象
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        Date date = dateFormat1.parse("2020年01月01日 12时12分12秒");
        System.out.println(date);

        // 测试其他的格式字符串，例如："D"， 获取本时间对象是所处的年中的第几天
        DateFormat dateFormat2 = new SimpleDateFormat("D");
        String str2 = dateFormat2.format(new Date());
        System.out.println(str2);

    }
}
