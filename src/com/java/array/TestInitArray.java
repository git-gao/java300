package com.java.array;

/**
 * 数组的初始化
 */
public class TestInitArray {

    public static void main(String[] args) {
        // 静态初始化
        int[] a = {1, 2, 3};
        int b[] = {1, 2, 3};

        // 默认初始化
        // 数组是引用类型，每一个元素相当于数组的实例化，int 默认是 0，boolean 默认值是 false
        int[] c = new int[2];
        boolean[] f = new boolean[2];
        System.out.println(c[0] + ", " + c[1]); // 0, 0
        System.out.println(f[0] + ", " + f[1]); // false, false

        // 动态初始化
        String[] ss = new String[2];// 默认值 null
        System.out.println(ss[0]); // null
        ss[0] = "aa";// 动态赋值
        System.out.println(ss[0]);// aa
    }

}
