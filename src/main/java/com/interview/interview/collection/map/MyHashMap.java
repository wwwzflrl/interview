package com.interview.interview.collection.map;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 使用拉链法
 */
public class MyHashMap<K, V> {
    private int size;
    private HashSlot<K, V>[] table;
    private static final int INIT_CAP = 4;
    public MyHashMap() {
        this(INIT_CAP);
    }

    public MyHashMap(int capacity) {
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
        MyHashMap<K, V> newMap = new MyHashMap<>(capacity);
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

    /**
     * One linked list in same hash bucket
     */
    private class HashSlot<K, V> {
        private final Node<K, V> head;
        private final Node<K, V> tail;

        @Getter
        private int size;

        public HashSlot() {
            this.head = new Node<>(null, null);
            this.tail = new Node<>(null, null);

            head.next = tail;
            this.size = 0;
        }

        public V put(K key, V val) {
            Node<K, V> p = getNode(key);

            if (p != null) {
                V oldVal = p.val;
                p.val = val;
                return oldVal;
            }

            Node<K, V> x = new Node<>(key, val);
            x.next = head.next;
            head.next = x;
            size++;

            return null;
        }

        public V remove(K key) {
            Node<K, V> prev = head;
            for (Node<K, V> p = head.next; p != tail; p = p.next) {
                if (key.equals(p.key)) {
                    prev.next = p.next;
                    size--;
                    return p.val;
                }
                prev = p;
            }
            return null;
        }

        public V get(K key) {
            Node<K, V> p = getNode(key);
            if (p == null) {
                return null;
            }
            return p.val;
        }

        public boolean containsKey(K key) {
            return getNode(key) != null;
        }

        public List<K> keys() {
            LinkedList<K> keyList = new LinkedList<>();

            Node<K, V> p = head.next;
            while (p != tail) {
                keyList.addLast(p.key);
                p = p.next;
            }

            return keyList;
        }

        public List<Map.Entry<K, V>> entries() {
            LinkedList<Map.Entry<K, V>> entryList = new LinkedList<>();
            Node<K, V> p = head.next;
            while (p != tail) {
                entryList.addLast(p);
                p = p.next;
            }
            return entryList;
        }

        private Node<K, V> getNode(K key) {
            for (Node<K, V> p = head.next; p != tail; p = p.next) {
                if (key.equals(p.key)) {
                    return p;
                }
            }
            return null;
        }
    }

    private static class Node<K, V> implements Map.Entry<K, V> {
        K key;
        V val;
        Node<K, V> next;
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
