package com.java.IOStream;

import java.io.*;

/**
 * 文件拷贝工具类
 */
public class FileUtils {
    public static void main(String[] args) {
        // 文件到文件
        try {
            InputStream is = new FileInputStream("F:\\\\ideaWorkspace\\\\java300\\\\test\\\\IOstream\\\\dir\\\\inputStream\\test1.txt");
            OutputStream os = new FileOutputStream("F:\\\\ideaWorkspace\\\\java300\\\\test\\\\IOstream\\\\dir\\\\inputStream\\copy_test2.txt");
            copy(is, os);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 文件到字节数组
        byte[] datas = null;
        try {
            InputStream is = new FileInputStream("F:\\ideaWorkspace\\java300\\test\\IOstream\\dir\\byteArrayStream\\timg.jpg");
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            copy(is, os);
            datas = os.toByteArray();
            System.out.println(datas.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        // 字节数组到文件
        try {
            InputStream is = new ByteArrayInputStream(datas);
            OutputStream os = new FileOutputStream("F:\\ideaWorkspace\\java300\\test\\IOstream\\dir\\byteArrayStream\\timg2.png");
            copy(is, os);
            System.out.println(datas.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * 对接输入输出流
     * @param is
     * @param os
     * @throws IOException
     */
    public static void copy(InputStream is, OutputStream os) {

        try {
            // 3. 读取
            byte[] flush = new byte[1024]; // 缓冲容器
            int len = -1;// 接收长度
            while ((len = is.read(flush)) != -1) {
                os.write(flush, 0, len);
            }
            os.flush();
            System.out.println("文件拷贝成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 释放资源，分别关闭，先打开的后关闭
            close(os, is);
        }
    }


    /**
     * close 方法都实现了 Closeable 接口，用可变参数来接收
     * @param ios
     */
    public static void close(Closeable...ios) {
        for (Closeable io: ios) {
            try {
                if (io != null) {
                    io.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
