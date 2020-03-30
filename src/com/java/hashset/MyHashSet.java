package com.java.hashset;

import java.util.HashMap;

/**
 * hashSet 实现
 * 底层原理是 hashMap
 */
public class MyHashSet {

    HashMap map;

    private static final Object PRESENT = new Object();

    public MyHashSet() {
        map = new HashMap();
    }

    public int size() {
        return map.size();
    }

    public void add(Object object) {
        map.put(object, PRESENT);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        for (Object key: map.keySet()) {
            sb.append(key + ",");
        }
        sb.setCharAt(sb.length()-1, '}');

        return sb.toString();
    }

    public static void main(String[] args) {
        MyHashSet set = new MyHashSet();
        set.add(2);
        set.add('c');
        set.add("ss");
        set.add(2);

        System.out.println(set);
        System.out.println(set.size());
    }
}
