package com.java.hashmap;

/**
 * hashMap 基类接口
 */
public interface BaseMap<K, V> {
    /**
     * 根据 key 值获取 value 值
     * @param k 键值
     * @return
     */
    public V get(K k);

    /**
     * 存放键值对到 hashMap 中
     * @param k key 值
     * @param v value 值
     * @return
     */
    public V put(K k, V v);
}
