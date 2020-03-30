package com.java.classload.demo;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 自定义解密类加载器
 */
public class DecrptClassLoader extends ClassLoader{
    // F:\ideaWorkspace\java300\src  com.java.classload.demo
    private String rootDir;

    public DecrptClassLoader(String rootDir) {
        this.rootDir = rootDir;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException{

        Class<?> c = findLoadedClass(name);

        // 先查询有没有加载过这个类，如果已经加载，则直接返回已加载的类，如果没有则加载新的类
        if (c != null) {
            return c;
        } else {
            try {
                c = this.getParent().loadClass(name); // 委派给父类加载
            } catch (ClassNotFoundException e) {
                //e.printStackTrace();
            }

            if (c != null) {
                return c;
            } else {
                byte[] classData = getClassData(name);
                if (classData == null) {
                    throw new ClassNotFoundException();
                } else {
                    c = defineClass(name, classData, 0, classData.length);
                }
            }
        }

        return c;
    }

    private byte[] getClassData(String className) {
        String path = rootDir + "/" + className.replace('.', '/') + ".class";

        InputStream inputStream = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {

            inputStream = new FileInputStream(path);// 打开一个文件输入流
            /*byte[] buffer = new byte[1024];
            // 将流中的数据转化为数组
            int temp = 0;
            while ((temp = inputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, temp);
            }*/
            int temp = -1;
            while ((temp = inputStream.read()) != -1){
                baos.write(temp^0xff);
            }

            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
