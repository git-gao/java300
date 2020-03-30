package com.java.IOStream.inputOutputStream;

import java.io.*;

/**
 * 文件字节输入流测试
 */
public class TestFileInputStream {
    public static void main(String[] args) {

        File dir = new File("F:\\ideaWorkspace\\java300\\test\\IOstream\\dir\\inputStream");
        dir.mkdirs();

        // 1. 创建源
        File src = new File(dir + "/test1.txt");

        // 2. 选择流
        InputStream is = null;
        try {
            is = new FileInputStream(src);
            // 3. 读取
            byte[] flush = new byte[5]; // 缓冲容器
            int len = -1;// 接收长度
            while ((len = is.read(flush)) != -1) {
                // 字节数组 ---> 字符串
                String str = new String(flush, 0, flush.length);
                System.out.println(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
