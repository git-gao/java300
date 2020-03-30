package com.java.IOStream.readerWriter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * 文件字符输入流
 */
public class TestFileReader {
    public static void main(String[] args) {
        File dir = new File("F:\\ideaWorkspace\\java300\\test\\IOstream\\dir\\readerWriter");
        dir.mkdirs();

        // 1. 创建源
        File src = new File(dir + "/test1.txt");

        // 2. 选择流
        Reader reader = null;
        try {
            reader = new FileReader(src);
            // 3. 读取
            char[] flush = new char[1024]; // 缓冲容器
            int len = -1;// 接收长度
            while ((len = reader.read(flush)) != -1) {
                // 字符数组 ---> 字符串
                String str = new String(flush, 0, flush.length);
                System.out.println(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
