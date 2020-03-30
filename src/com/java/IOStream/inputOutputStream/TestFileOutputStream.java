package com.java.IOStream.inputOutputStream;

import java.io.*;

/**
 * 文件字节输出流
 */
public class TestFileOutputStream {
    public static void main(String[] args) throws IOException {

        File dir = new File("F:\\ideaWorkspace\\java300\\test\\IOstream\\dir\\inputStream");
        dir.mkdirs();// 创建文件夹
        File file = new File(dir + "/test2.txt");
        file.createNewFile();// 创建文件

        //File src = new File(file.getAbsolutePath());

        OutputStream out = null;

        try {
            out = new FileOutputStream(file);
            String msg = "mnbvcxz kkk";
            byte[] datas = msg.getBytes();
            out.write(datas, 0, datas.length);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
