package com.java.IOStream.byteArray;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 程序到字节数组
 * 字节数组输出流
 * 1. 创建源：内部维护
 * 2. 选择流：不关联源
 * 3. 操作（写出内容）
 * 4. 释放资源（可以不处理）
 */
public class TestByteArrayOutputStream {

    public static void main(String[] args) throws IOException {
        // 1. 创建源
        byte[] dest = null;
        // 2. 选择流
        ByteArrayOutputStream baos = null;

        baos = new ByteArrayOutputStream();
        // 3. 操作，写出
        String msg = "show me the code";
        // 字符串 ---> 字节数组
        byte[] datas = msg.getBytes();
        baos.write(datas, 0 , datas.length);
        baos.flush();

        // 获取数据
        dest = baos.toByteArray();
        System.out.println(dest.length + "--->" + new String(dest, 0, baos.size()) + "--->" + baos.size());
    }
}
