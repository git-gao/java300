package com.java.classload;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * 自定义文件系统加载器测试类
 */
public class TestFileSystem {

    public static void main(String[] args) throws Exception {
        String rootDir = "F:\\ideaWorkspace\\java300\\src";
        String path = rootDir + "/" + "com.java.classload".replace('.', '/');

        // 动态编译
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        int result = javaCompiler.run(null, null, null, path + "/" + "TestDemo.java");
        System.out.println(result == 0 ? "编译成功": "编译失败");

        FileSystemClassLoader loader1 = new FileSystemClassLoader(rootDir);
        FileSystemClassLoader loader2 = new FileSystemClassLoader(rootDir);


        // 首先调用 Classloader 的loadclass，如果其父类加载不到，则执行自定义类加载器的 findClass() 方法进行加载
        Class<?> classLoader1 = loader1.loadClass("com.java.classload.TestDemo");
        Class<?> classLoader2 = loader1.loadClass("com.java.classload.TestDemo");

        Class<?> classLoader3 = loader2.loadClass("com.java.classload.TestDemo");

        Class<?> classLoader4 = loader2.loadClass("java.lang.String");

        System.out.println(classLoader1);
        System.out.println(classLoader1.hashCode());
        System.out.println(classLoader2.hashCode());// classLoader1, classLoader2 属于同一个类加载器加载的类，属于同一个类
        System.out.println(classLoader3.hashCode());// classLoader3 不同的类加载器加载出来的类，不属于同一个类

        System.out.println("加载器:" + classLoader1.getClassLoader());// 自定义类加载器
        System.out.println("加载器:" + classLoader4.getClassLoader());// 引导类加载器

    }
}
