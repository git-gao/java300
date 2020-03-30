package com.java.hashmap;

public class Entry<K, V> implements BaseEntry<K, V> {

    K k;
    V v;
    int hash;
    Entry<K, V> next;

    /**
     * 构建结点
     * @param k
     * @param v
     * @param next
     */
    public Entry(K k, V v, int hash, Entry<K, V> next) {
        this.k = k;
        this.v = v;
        this.hash =hash;
        this.next = next;
    }

    @Override
    public K getKey() {
        return k;
    }

    @Override
    public V getValue() {
        return v;
    }

    public int getHash() {
        return hash;
    }
}
