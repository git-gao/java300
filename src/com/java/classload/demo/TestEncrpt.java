package com.java.classload.demo;


import com.java.classload.FileSystemClassLoader;

/**
 * 加密解密工具类的测试
 */
public class TestEncrpt {

    public static void main(String[] args) throws Exception {
        String path = "F:\\ideaWorkspace\\java300\\test\\classload\\temp";
        // 正常的类加载器加载不到，ClassFormatError
        /*FileSystemClassLoader loader = new FileSystemClassLoader(path);
        Class<?> aClass = loader.loadClass("TestDemo");*/

        // 使用解密的类加载器
        DecrptClassLoader loader1 = new DecrptClassLoader(path);
        Class<?> clazz = loader1.loadClass("TestDemo");
        System.out.println(clazz);
    }

}
