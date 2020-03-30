package com.java.iterator;

import java.util.*;

/**
 * List, Set, Map 使用 iterator 迭代器进行遍历
 */
public class TestIterator {

    /**
     * List 迭代器测试
     *
     * Collection 接口继承了 Iterable 接口，Iterable 接口内部创建了 Iterator 对象
     * 通过 Iterable 接口能获取 Iterator 对象，Iterator 接口实现了 hasNext() 和 next() 方法
     * List 继承了 Collection 接口
     */
    public static void testIteratorList() {
        List<String> list = new ArrayList<>();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        list.add("bb");

        // 创建一个list 的迭代器
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // for 遍历方式
        for (Iterator<String> iterator1 = list.iterator(); iterator1.hasNext();) {
            System.out.println(iterator1.next());
        }
    }

    /**
     * Set 迭代器测试
     *
     * Collection 接口继承了 Iterable 接口，Iterable 接口内部创建了 Iterator 对象
     * 通过 Iterable 接口能获取 Iterator 对象，Iterator 接口实现了 hasNext() 和 next() 方法
     * Set 继承了 Collection 接口
     */
    public static void testIteratorSet() {
        Set<String> set = new HashSet<>();
        set.add("111");
        set.add("222");
        set.add("333");
        set.add("222");

        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     * Map 迭代器测试
     * 通过 Map 接口可以创建 set 对象，利用迭代器进行遍历
     * 第一种：map.entrySet()，存储的是键值对
     * 第二种：map.keySet()，存储的是键
     */
    public static void testIteratorMap() {
        Map<String, String> map = new HashMap<>();
        map.put("111", "aaa");
        map.put("222", "bbb");
        map.put("333", "ccc");
        map.put("222", "eee");

        // entrySet 存储的是 map 中的键值对
        System.out.println("map 的第一种遍历方式");
        Set<Map.Entry<String, String>> entries = map.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> item = iterator.next();
            System.out.println(item.getKey() + ": " + item.getValue());
        }

        System.out.println("map 的第二种遍历方式");
        Set<String> keySet = map.keySet();
        Iterator<String> iterator1 = keySet.iterator();
        while (iterator1.hasNext()) {
            String key = iterator1.next();
            System.out.println(key + ": " + map.get(key));
        }

        // 使用增强 for 循环进行遍历, for-each 循环只能读取不能修改元素
        System.out.println("map 使用增强 for 循环进行遍历");
        for (String k: keySet) {
            System.out.println(k + ": " + map.get(k));
        }

    }

    public static void main(String[] args) {
        // list 迭代器测试
        System.out.println("---list 迭代器测试---");
        testIteratorList();
        // set 迭代器测试
        System.out.println("---set 迭代器测试---");
        testIteratorSet();
        // map 迭代器测试
        System.out.println("---map 迭代器测试---");
        testIteratorMap();
    }
}
