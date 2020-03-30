package com.java.IOStream.file;

import java.io.File;
import java.lang.reflect.Field;


/**
 * 创建目录
 * mkdir(),确保上级目录存在，不存在创建失败
 * mkdirs(), 上级目录不存在则一起创建
 *
 * list(), 列出目录下所有文件夹
 * listFiles(), 列出目录下所有文件对象
 */
public class TestDir {

    // psvm
    public static void main(String[] args) {
        // 创建文件夹，上级目录不存在则一同创建
        File dir = new File("F:\\ideaWorkspace\\java300\\test\\IOstream\\dir");
        boolean flag = dir.mkdirs();
        System.out.println(flag);

        // 创建文件夹，上级目录必须存在
        File dir1 = new File("F:\\ideaWorkspace\\java300\\test\\IOstream\\dir\\file");
        boolean flag1 = dir1.mkdir();
        System.out.println(flag1);

        // 列出所有文件
        File dir2 = new File("F:\\ideaWorkspace\\java300\\src\\com\\java");
        System.out.println("列出下级所有文件");

        // 列出下级文件 list()
        String[] subNames = dir2.list();
        for (String s: subNames) {
            System.out.println(s);
        }

        System.out.println("列出下级所有文件对象");
        // 下级对象 listFiles()
        File[] subFiles = dir2.listFiles();
        for (File file: subFiles) {
            System.out.println(file.getName() + "," + file.getAbsolutePath());
        }

        // 递归打所有目录
        System.out.println("递归打所有目录");
        File file = new File("F:\\ideaWorkspace\\java300\\src\\com\\java");
        printNames(file, 0);

        count(file);
        System.out.println(len);
    }

    /**
     * 打印文件夹下的所有文件
     * @param file
     * @param deep
     */
    public static void printNames(File file, int deep) {

        for (int i = 0; i < deep; i++) {
            System.out.print("-");
        }

        System.out.println(file.getName() + "*****" + file.getPath());
        if (null == file || !file.exists()) {
            return;
        } else if (file.isDirectory()){
            for (File f: file.listFiles()) {
                printNames(f, deep+1);
            }
        }

    }

    public static long len = 0;

    /**
     * 统计文件的大小
     * @param file
     */
    public static void count(File file) {

        if (null != file && file.exists()) {
            if (file.isFile()) {
                len += file.length();
            } else {
                for (File f: file.listFiles()) {
                    count(f);
                }
            }
        }
    }
}
