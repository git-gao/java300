package com.java.IOStream.inputOutputStream;

import java.io.*;

/**
 * 利用递归制作文件夹的拷贝
 * 讲一个文件夹的内容全部拷贝到另一个文件
 */
public class TestCopyDir {

    public static void copy(String src, String outFile) throws IOException {


        //File src = new File(file.getAbsolutePath());
        // 1. 创建源
        File srcFile = new File(src);
        File targetFile = new File(outFile);

        // 2. 选择流
        InputStream is = null;
        OutputStream out = null;

        try {
            is = new FileInputStream(srcFile);
            out = new FileOutputStream(targetFile);
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

    /**
     * 如果是文件夹则新建文件夹，如果是文件则拷贝文件内容
     * @param file
     */
    public static void recursionDir(File file, File targetFile) throws IOException {
        if (null != file && file.exists()) {
            if (file.isDirectory()) {
                for (File f: file.listFiles()) {
                    // 如果是文件类型，则进行拷贝
                    if (f.isFile()) {
                        copy(f.getPath(), targetFile + "/" + f.getName());
                    } else if (f.isDirectory()) {
                        // 用目标目录 + 源目录名称
                        File target = new File(targetFile + "/" + f.getName());
                        target.mkdirs();// 在目标目录下创建一个和源目录一样的文件夹
                        // 文件夹类型，继续递归
                        recursionDir(f, target);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String src = "F:\\ideaWorkspace\\java300\\src\\com\\java\\IOStream";
        String target = "F:\\ideaWorkspace\\java300\\test\\IOstream";
        recursionDir(new File(src), new File(target));
    }
}
