package com.java.classload.demo;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;

/**
 * 加密工具类
 * 对class文件进行加密
 */
public class EncrptUtil {

    public static void encrpt(String src, String dest) {
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(src);
            fos = new FileOutputStream(dest);

            // 文件写入加密
            int temp = -1;
            while ((temp = fis.read()) != -1) {
                fos.write(temp^0xff); // 取反
            }
            System.out.println("加密成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] var) {
        String path = "F:\\ideaWorkspace\\java300\\test\\classload";
        String ipath = "F:\\ideaWorkspace\\java300\\test\\classload\\TestDemo.class";
        // 动态编译
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        int result = javaCompiler.run(null, null, null, path + "/" + "TestDemo.java");
        System.out.println(result == 0 ? "编译成功": "编译失败");

        String opath = "F:\\ideaWorkspace\\java300\\test\\classload\\temp\\TestDemo.class";
        encrpt(ipath, opath);
    }
}
