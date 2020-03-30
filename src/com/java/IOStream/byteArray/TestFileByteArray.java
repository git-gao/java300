package com.java.IOStream.byteArray;

import java.io.*;

/**
 * 1. 图片读取到字节数组
 * 2. 字节数组写出到文件
 */
public class TestFileByteArray {

    public static void main(String[] args) {
        String dir = "F:\\ideaWorkspace\\java300\\test\\IOstream\\dir\\byteArrayStream";
        byte[] datas = file2ByteArray(dir + "/timg.jpg");
        System.out.println(datas.length);
        byteArray2File(datas, dir + "/timg1.jpg");
    }

    /**
     * 图片到字节数组
     * 图片到程序 FileInputStream
     * 程序到字节数组 ByteArrayOutputStream
     */
    public static byte[] file2ByteArray(String filePath) {
        // 1. 创建源
        File src = new File(filePath);
        byte[] dest = null;

        // 2. 选择流
        InputStream is = null;
        ByteArrayOutputStream baos= null;

        try {
            is = new FileInputStream(src);
            baos = new ByteArrayOutputStream();

            // 3. 操作（分段读取)
            byte[] flush = new byte[1024*10];
            int len = -1;
            while ((len = is.read(flush)) != -1) {
                baos.write(flush, 0, len);// 这里 len 应为实际读取的长度，而不是flush.length
            }
            baos.flush();
            return baos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 释放资源
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 字节数组到图片
     * 字节数组到程序 ByteArrayInputStream
     * 程序到文件 FileOutputStream
     */
    public static void byteArray2File(byte[] src, String filePath) {
        // 1. 创建流
        File dest = new File(filePath);
        // 2. 选择流
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new ByteArrayInputStream(src);
            os = new FileOutputStream(dest);
            // 3. 分段读取
            byte[] flush = new byte[1024];
            int len = -1;
            while ((len = is.read(flush)) != -1) {
                os.write(flush, 0, len);
            }
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
