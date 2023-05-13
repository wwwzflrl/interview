package com.interview.interview.collection.map;

import lombok.Getter;

import java.util.Map;

/**
 * 使用线性探查法
 */
public class MyHashListMap<K, V> {
    private Node<K, V>[] tables;
    @Getter
    private int size;
    private static final int INIT_CAP = 4;

    public MyHashListMap() {
        this(INIT_CAP);
    }

    public MyHashListMap(int capacity) {
        size = 0;
        tables = new Node[capacity];
    }

    public V put(K key, V val) {
        if (size >= tables.length / 2) {
            resize( tables.length * 2);
        }

        int i = getNodeIndex(key);

        // key 已存在，修改对应的 val
        if (i != -1) {
            Node<K, V> entry = tables[i];
            V oldVal = entry.val;
            entry.val = val;
            return oldVal;
        }

        // key 不存在，找个空位插入
        i = hash(key);
        while (tables[i] != null) {
            i = (i + 1) % tables.length;
        }
        // 此时 table[i] 为一个空位
        Node<K, V> x = new Node<>(key, val);
        tables[i] = x;
        size++;

        return null;
    }

    // 删除 key 和对应的 val，并返回 val
    // 若 key 不存在，则返回 null
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }

        if (size <  tables.length  / 8) {
            resize( tables.length  / 2);
        }

        int i = getNodeIndex(key);

        if (i == -1) {
            // key 不存在，不需要 remove
            return null;
        }

        // 开始 remove
        V deletedVal = tables[i].val;
        tables[i] = null;
        size--;
        // 保持连续性 important, otherwise getNodeIndex() will not return right value
        i = (i + 1) % tables.length;
        for (; tables[i] != null; i = (i + 1) % tables.length) {
            Node<K, V> entry = tables[i];
            tables[i] = null;
            // put 里面又会加一
            size--;
            put(entry.key, entry.val);

        }

        return deletedVal;
    }

    private int getNodeIndex(K key) {
        int i;
        for (i = hash(key); tables[i] != null; i = (i + 1) % tables.length) {
            if (tables[i].key.equals(key))
                return i;
        }
        return -1;
    }

    private int hash(K key) {
        // int: 0000 0000 0000 ... 0000
        //    : 0111 1111 1111 ... 1111
        return (key.hashCode() & 0x7fffffff) % tables.length;
    }



    private void resize(int capacity) {
        MyHashListMap<K, V> newMap = new MyHashListMap<>(capacity);
        for (Node<K, V> entry : tables) {
            if (entry != null) {
                newMap.put(entry.key, entry.val);
            }
        }
        this.tables = newMap.tables;
    }


    private static class Node<K, V> implements Map.Entry<K, V> {
        K key;
        V val;

        Node(K key, V val) {
            this.key = key;
            this.val = val;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return val;
        }

        @Override
        public V setValue(V val) {
            V oldVal = this.val;
            this.val = val;
            return oldVal;
        }
    }
}
