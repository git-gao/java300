package com.java.hashmap;

import java.util.HashMap;

/**
 * 泛型类的 hashMap
 */
public class MyHashMap2<K, V> implements BaseMap<K, V> {

    private int defaultLength = 8;//默认长度
    private double defaultAddFactor = 0.75;//默认负载因子
    private int useSize;    // 数组已使用大小
    private Entry<K ,V>[] table;    //数组

    /**
     * 初始化构造方法
     */
    public MyHashMap2() {
        this(8, 0.75);
    }

    public MyHashMap2(int defaultLength, double defaultAddFactor) {
        if (defaultLength < 0) {
            throw new IllegalArgumentException("数组异常");
        }
        HashMap hashMap = new HashMap();

        if (defaultAddFactor <= 0 || Double.isNaN(defaultAddFactor)) {
            throw new IllegalArgumentException("负载因子异常");
        }
        this.defaultLength = defaultLength;
        this.defaultAddFactor = defaultAddFactor;
        table = new Entry[defaultLength];
    }


    /**
     *
     * @param k 键值
     * @return value 值
     */
    @Override
    public V get(K k) {
        int hash = myHash(k.hashCode(), table.length);
        Entry<K, V> entry = table[hash];
        V v = null;
        if (entry == null) {
            throw new NullPointerException();
        }

        // 如果此处数组元素不为空，则遍历此处链表
        while (entry != null) {
            if (entry.getKey().equals(k)) {
                v = entry.getValue();
                break;
            }
            entry = entry.next;
        }
        System.out.println("get 方法:" + v);
        return v;
    }

    /**
     *
     * @param k key 值
     * @param v value 值
     * @return
     */
    @Override
    public V put(K k, V v) {
        // 如果数组大小大于 数组容量 * 负载因子，则扩容
        if (useSize > defaultLength * defaultAddFactor) {
            resize();
        }
        // 计算 key 值在数组中的索引
        int hash = myHash(k.hashCode(), defaultLength);
        // 获取该索引处的数组元素
        Entry<K, V> entry = table[hash];
        // 创建一个新结点
        Entry<K, V> entryNew = new Entry<>(k, v, hash, null);

        // 如果此处数组中元素为空，直接将新结点放入
        if (entry == null) {
            table[hash] = entryNew;
            useSize++;
        } else {
            // 如果此处不为空
            Entry<K, V> lastNode = null;
            while (entry != null) {
                // 判断是否存在相同的 key ,有则替换掉 value 值
                if (entry.getKey() == k || (entry.getKey() != null && entry.getKey().equals(k))) {
                    System.out.println("put 方法，key 重复了！");
                    entry.v = v;
                    return entry.getValue();
                } else {
                    lastNode = entry;
                    entry = entry.next;
                }
            }
            lastNode.next = entryNew;
        }
        return entryNew.getValue();
    }

    /**
     * hashMap 扩容，将原数组元素取出，放入新数组中
     * 以初始值的 2 倍进行扩容，2的次幂减少碰撞次数
     */
    public void resize() {
        // 定义一个新的数组
        defaultLength = defaultLength << 1;
        Entry<K, V>[] newTable = new Entry[defaultLength];
        for (int i = 0; i < table.length; i++) {
            // 获取原数组的每个元素
            Entry<K, V> entry = table[i];
            if (entry != null) {
                table[i] = null;    // 释放原数组的对象引用
                while (entry != null) {
                    Entry<K, V> next = entry.next;
                    // 重新计算每个元素在数组中的位置
                    int hash = myHash(entry.getKey().hashCode(), newTable.length);
                    entry.hash = hash;// 重新设置元素 hash 值
                    entry.next = newTable[hash];// 头插法
                    newTable[hash] = entry;
                    entry = next;   // 访问 entry 链的下一个元素
                }
            }
        }
        //table = newTable;
    }

    /**
     * 计算 hash 值， 既数组索引
     * @param hashCode key 值的 hashCode 值
     * @param length   数组长度
     * @return
     */
    public int myHash(int hashCode, int length) {
        return hashCode & (length - 1);
    }

    public static void main(String[] args) {
        MyHashMap2 map = new MyHashMap2();
        map.put(22, "22");
        map.put(54, "54");
        map.put(38, "38");
        map.put(10, "aa");
        map.put(19, "bb");
        map.put(20, "cc");
        map.put(24, "dd");
        map.put(28, "dd");
        map.put(31, "ee");
        map.put(25, "fff");
        map.put(32, "sss");

        map.get(28);

        int a = 0;
    }
}
