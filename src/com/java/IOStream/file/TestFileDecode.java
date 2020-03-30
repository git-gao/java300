package com.java.IOStream.file;

import java.io.UnsupportedEncodingException;

public class TestFileDecode {

    public static void main(String[] args) throws UnsupportedEncodingException {

        String str = "今天星期一A";

        byte[] data = str.getBytes();
        /**
         * 乱码
         * 1. 字节数不够
         * 2. 字符集不统一
         */
        str = new String(data, 0, data.length-1, "utf-8");
        System.out.println(str);
        str = new String(data, 0, data.length-2, "utf-8");
        System.out.println(str);

        str = new String(data, 0, data.length, "gbk");
        System.out.println(str);
    }
}
