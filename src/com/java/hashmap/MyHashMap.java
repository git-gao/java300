package com.java.hashmap;


/**
 * 简单实现的hashMap
 */
public class MyHashMap {

    Node[] table;   // 位桶数组
    int size;       // 存放的键值对个数

    public MyHashMap() {
        table = new Node[16];
    }

    /**
     * 获取元素
     * @param key
     * @return
     */
    public Object get(Object key) {
        int hash = myHash(key.hashCode(), table.length);
        Object value = null;
        Node node = table[hash];
        if (node == null) {
            throw new NullPointerException();
        }
        while (node != null) {
            if (key.equals(node.key)) {
                value = node.value;
                break;
            }
            node = node.next;
        }
        System.out.println("get 方法:" + value);
        return value;
    }

    /**
     * 添加元素
     * @param key
     * @param value
     */
    public void put(Object key, Object value) {
        Node node = new Node();
        node.hash = myHash(key.hashCode(), table.length);
        node.key = key;
        node.value = value;
        node.next = null;

        // tnode 数组中存储的链表
        Node tnode = table[node.hash];
        // 此处数组元素为空，直接将新节点放进去
        if (tnode == null) {
            table[node.hash] = node;
            size++;
        } else {
            Node last = null; // 最后一位结点
            // 此处数组内容不为空，则遍历对应链表
            while (tnode != null) {
                // 判断 key 值，如果重复，则覆盖
                if (tnode.key.equals(key)) {
                    System.out.println("put 方法，key 重复了！");
                    tnode.value = value;
                    return;
                } else {
                    // 不重复，则遍历下一个
                    last = tnode;// 保存最后一位元素
                    tnode = tnode.next;
                }
            }
            last.next = node;// 将新结点加入到最后一位结点的后面
            size++;
            /*// 写法二
            Node t = tnode;
            if (t.key.EqualsTest(key)) {
                System.out.println("key 重复了！");
                t.value = value;
            } else {
                while (t.next != null) {
                    if (t.next.key.EqualsTest(key)) {
                        t.next.value = value;
                        return;
                    } else {
                        t = t.next;
                    }
                }
                if (t.next == null) {
                    t.next = node;
                }
            }*/
        }
    }

    /**
     * hash 计算方法
     * @param v
     * @param length
     * @return
     */
    public static int myHash(int v, int length) {
        //System.out.println("hash:" + (v & (length-1))); // 直接运算，效率高
        //System.out.println("hash:" + (v % length));// 取模运算，效率低
        return v & (length-1);
    }

    @Override
    public String toString() {

        StringBuilder sbf = new StringBuilder("{");
        // 遍历数组
        for (int i = 0; i < table.length; i++) {
            // 获取数组中的结点
            Node tnode = table[i];
            while (tnode != null) {
                sbf.append(tnode.key + ": " + tnode.value + ",");
                tnode = tnode.next;
            }
        }
        sbf.setCharAt(sbf.length()-1, '}');
        return sbf.toString();
    }

    public static void main(String[] args) {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put(10, "aa");
        myHashMap.put(20, "bb");
        myHashMap.put(30, "cc");
        myHashMap.put(20, "sss");
        myHashMap.put(22, "22");
        myHashMap.put(54, "54");
        myHashMap.put(38, "38");

        //System.out.println(myHashMap.toString());
        myHashMap.get(20);

        // 获取 hash
        /*for (int i = 0; i < 100; i++) {
            System.out.println(i + ":.......:" + myHash(i, 15));
        }*/
    }
}
