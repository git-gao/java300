package com.java.reflection;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 利用反射机制获取类的所有信息
 */
public class TestReflection {
    public static void main(String[] args) {
        String path = "com.java.reflection.User";

        try {
            User user = new User();
            // 一個类被加载后，JVM 会创建一个该类的 Class 对象， 这个类的整个结构信息会放到这个 Class 对象中
            // 这个 Class 对象就像一面镜子，通过这面镜子可以看到对应类的全部信息
            Class clazz = Class.forName(path);
            Class clazz1 = Class.forName(path);
            System.out.println(clazz);
            System.out.println(clazz + ", " + clazz.hashCode());// 一个类只对应一个 Class 对象
            System.out.println(clazz1 + ", " + clazz1.hashCode());// 一个类只对应一个 Class 对象

            // 获取类的对象的三种方式
            Class clazz2 = User.class;
            System.out.println(clazz2 + ", " + clazz2.hashCode());
            Class clazz3 = user.getClass();
            System.out.println(clazz3 + ", " + clazz3.hashCode());

            // 获取类的属性
            Field[] fields = clazz.getFields();// 只能获取 public 属性
            System.out.println(fields.length);
            Field field = clazz.getDeclaredField("name");
            System.out.println("获取单个属性：" + field);
            Field[] fields1 = clazz.getDeclaredFields(); // 获取所有类型的属性
            for (Field f : fields1) {
                System.out.println("属性：" + f);
            }

            // 获取类的方法
            Method[] methods = clazz.getMethods();//getMethods 获取所有的 public 方法
            System.out.println("方法个数：" + methods.length);// 这里包括默认继承了的 Object 类方法
            for (Method m: methods) {
                System.out.println("方法：" + m);
            }
            Method[] methods1 = clazz1.getDeclaredMethods(); // getDeclaredMethods 获取所有的方法
            System.out.println("方法个数：" + methods1.length);// 只获取 User 类的方法
            for (Method m: methods1) {
                System.out.println("方法：" + m);
            }
            // 获取某个方法
            Method method = clazz.getMethod("getName");
            System.out.println("获取单个方法：" + method);
            // 如果方法有参数，则必须传递对应类型的 Class 对象
            Method method1 = clazz.getMethod("getName", String.class);
            System.out.println("获取带参数的方法：" + method1);

            // 获取构造器的方法
            Constructor[] constructors = clazz.getDeclaredConstructors(); // 获取所有的构造器
            for (Constructor constructor: constructors) {
                System.out.println("类的构造器:" + constructor);
            }
            Constructor constructor = clazz.getDeclaredConstructor(int.class, String.class, int.class); // 获取指定类型的构造器
            System.out.println("获取指定参数类型的构造器：" + constructor);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
