package com.java.IOStream.inputOutputStream;

import java.io.*;

/**
 * 文件拷贝
 */
public class TestCopyFile {

    public static void copy(String fromSrc, String copySrc) {
        File dir = new File("F:\\ideaWorkspace\\java300\\test\\IOstream\\dir\\inputStream");
        dir.mkdirs();// 创建文件夹
        // 1. 创建源
        File src = new File(dir + "/" +fromSrc);
        File outFile = new File(dir + "/" + copySrc);
        // 创建拷贝的文件
        //outFile.createNewFile();// 创建文件

        //File src = new File(file.getAbsolutePath());

        // 2. 选择流
        InputStream is = null;
        OutputStream out = null;

        try {
            is = new FileInputStream(src);
            out = new FileOutputStream(outFile);
            // 3. 读取
            byte[] flush = new byte[2]; // 缓冲容器
            int len = -1;// 接收长度
            while ((len = is.read(flush)) != -1) {
                out.write(flush, 0, len);// 文件test1.txt 读入到 flush 中，立即写入到输出流中，读多少写多少
            }
            System.out.println("文件拷贝成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 4. 释放资源，分别关闭，先打开的后关闭
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {

        copy("test1.txt", "copy_test1.txt");

    }
}
