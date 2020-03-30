package com.java.IOStream.byteArray;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 字节数组到程序
 * 1. 创建源：字节数组不要太大
 * 2. 选择流
 * 3. 操作
 * 4. 释放资源，可以不用处理
 */
public class TestByteArrayInputStream {

    public static void main(String[] args) {
        File dir = new File("F:\\ideaWorkspace\\java300\\test\\IOstream\\dir\\byteArrayStream");
        dir.mkdirs();

        // 1. 创建源
        //File src = new File(dir + "/test1.txt");

        byte[] src = "take is cheap, show me the code".getBytes();

        // 2. 选择流
        InputStream is = null;
        try {
            is = new ByteArrayInputStream(src);
            // 3. 读取
            byte[] flush = new byte[10]; // 缓冲容器
            int len = -1;// 接收长度
            while ((len = is.read(flush)) != -1) {
                // 字节数组 ---> 字符串
                String str = new String(flush, 0, flush.length);
                System.out.println(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            // 可以不关闭资源
            /*try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }
    }
}
