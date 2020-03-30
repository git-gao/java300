package com.java.generic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 泛型测试
 */
public class TestGeneric {

    public static void main(String[] args) {
        MyCollection<String> myCollection = new MyCollection<>();
        myCollection.set("sss", 1);
        myCollection.set("aaa", 3);

        String res = myCollection.get(3);
        System.out.println(res);
    }
 }

/**
 * 自定义的泛型类
 * @param <E>
 */
class MyCollection<E> {
    Object[] obj = new Object[5];

    public void set(E e, int index) {
        obj[index] = e;
    }

    public E get(int index) {
        return (E) obj[index];
    }
}
