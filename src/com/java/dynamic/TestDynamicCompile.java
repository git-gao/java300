package com.java.dynamic;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 动态编译测试
 */
public class TestDynamicCompile {

    public static void main(String[] args) throws Exception{
        // 动态编译
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        String path = "F:\\ideaWorkspace\\java300\\src\\com\\java\\dynamic\\";
        String pack = "com.java.dynamic"; // 运行失败无法加载主类，要在上级目录加 包名.类型
        int result = javaCompiler.run(null, null, null, path + "Hello.java");
        System.out.println(result == 0 ? "编译成功": "编译失败");

        // 动态运行
        System.out.print("动态:");
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("java -cp F:\\ideaWorkspace\\java300\\src com.java.dynamic.Hello");
        // 创建输入流
        InputStream inputStream = process.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String info = null;
        while ((info = bufferedReader.readLine()) != null) {
            System.out.println(info);
        }

        // 利用反射机制动态运行
        System.out.print("反射:");
        try {
            URL[] urls = new URL[] {new URL("file:/"+ path)};
            URLClassLoader loader = new URLClassLoader(urls);
            Class clazz = loader.loadClass("com.java.dynamic.Hello");
            // 调用加载类的 main 方法
            Method method = clazz.getDeclaredMethod("main", String[].class);
            method.invoke(null, (Object) new String[] {"aaa", "bbb"});// 这里需要转化为 Object 类型，否则就是两个参数”aaa“，”bbb“,参数不匹配
            // 不转为 Object 相当于 method.invoke(null, "aaa", "bbb");
            // 因为接受的是一个可变参数，如果传入的是一个数组，可变参数自动将其识别为多个参数，所以需要转为 Object 类型
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
