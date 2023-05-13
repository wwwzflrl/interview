package com.interview.interview.collection.map;

import java.util.Map;

/**
 * 使用拉链法
 */
public class MyHashSlotMap<K, V> {
    private int size;
    private HashSlot<K, V>[] table;
    private static final int INIT_CAP = 4;
    public MyHashSlotMap() {
        this(INIT_CAP);
    }

    public MyHashSlotMap(int capacity) {
        size = 0;

        table = new HashSlot[capacity];
        for (int i = 0; i < table.length; i++) {
            table[i] = new HashSlot<>();
        }
    }

    public V put(K key, V val) {
        if (size >= table.length * 0.75) {
            resize(table.length * 2);
        }
        HashSlot<K, V> slot = table[hash(key)];
        // 如果 key 之前不存在，则插入，size 增加
        if (!slot.containsKey(key)) {
            size++;
        }
        return slot.put(key, val);
    }

    public V remove(K key) {
        HashSlot<K, V> slot = table[hash(key)];
        // 如果 key 之前存在，则删除，size 减少
        if (slot.containsKey(key)) {
            size--;
            return slot.remove(key);
        }
        return null;
    }

    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        HashSlot<K, V> slot = table[hash(key)];
        return slot.get(key);
    }

    public boolean containsKey(K key) {
        HashSlot<K, V> slot = table[hash(key)];
        return slot.containsKey(key);
    }

    /**
     * Map Key hash to table length
     */
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % table.length;
    }

    private void resize(int capacity) {
        MyHashSlotMap<K, V> newMap = new MyHashSlotMap<>(capacity);
        // 穷举当前 HashMap 中的所有键值对
        for (HashSlot<K, V> slot : table) {
            for (Map.Entry<K, V> entry : slot.entries()) {
                K key = entry.getKey();
                V val = entry.getValue();
                newMap.put(key, val);
            }
        }
        this.table = newMap.table;
    }
}
