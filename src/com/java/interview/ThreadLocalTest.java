package com.java.interview;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadLocalTest {

    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(new SimpleDateFormatDemo()).start();
        new Thread(new SimpleDateFormatDemo()).start();
    }

    public static DateFormat getDateFormat() {
        DateFormat df = threadLocal.get();
        if (df == null) {
            df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            threadLocal.set(df);
        }
        System.out.println(threadLocal);
        return df;
    }

    static class SimpleDateFormatDemo implements Runnable{

       private String strDate = "2021-04-24 12:00:00";

       @Override
       public void run() {
           Date date = null;
           try {
               date = getDateFormat().parse(strDate);
           } catch (ParseException e) {
               e.printStackTrace();
           }

           System.out.println(Thread.currentThread().getName() + " 当前时间：" + date);
       }
    }



    /*public static void main(String[] args) {
        new Thread(new SimpleDateFormatDemo()).start();
        new Thread(new SimpleDateFormatDemo()).start();
    }

    static class SimpleDateFormatDemo implements Runnable{

        private static final ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>()
        {
            @Override
            protected DateFormat initialValue() {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }
        };

        private String strDate = "2021-04-24 12:00:00";

        @Override
        public void run() {
            Date date = null;
            try {
                date = threadLocal.get().parse(strDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " 当前时间：" + date);
        }
    }*/

}


