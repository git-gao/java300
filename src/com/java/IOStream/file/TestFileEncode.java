package com.java.IOStream.file;

import java.io.UnsupportedEncodingException;

/**
 * 字符集编码测试
 */
public class TestFileEncode {

    public static void main(String[] args) throws Exception {
        String str = "今天星期一A";

        // 默认与工程编码方式保持一致，utf-8, 变长unicode(1-3字节) 中文3个字节
        byte[] data = str.getBytes();
        System.out.println(data.length);

        // 编码：其他字符集
        data = str.getBytes("unicode");
        System.out.println(data.length);

        // 定长 unicode 2个字节
        data = str.getBytes("UTF-16LE");
        System.out.println(data.length);

        // 变长中文2个字节
        data = str.getBytes("GBK");
        System.out.println(data.length);

    }
}
