package com.interview.interview.collection.map;

import com.interview.interview.collection.list.MyArrayList;

import java.util.Random;

public class MyArrayHashMap<K, V> {
    private final MyHashMap<K, Integer> map = new MyHashMap<>();

    private final MyArrayList<Node<K, V>> arr = new MyArrayList<>();

    private final Random r = new Random();

    public MyArrayHashMap() {}

    public V get(K key) {
        if (!map.containsKey(key)) {
            return null;
        }
        // 获取 key 在 map 中的索引
        int i = map.get(key);
        return arr.get(i).val;
    }

    public V put(K key, V val) {
        if (map.containsKey(key)) {
            int i = map.get(key);
            Node<K, V> x = arr.get(i);
            V oldVal = x.val;
            x.val = val;
            return oldVal;
        }

        // 新增
        Node<K, V> x = new Node<>(key, val);
        arr.add(x);
        map.put(key, arr.getSize() - 1);
        return null;
    }

    public V remove(K key) {
        if (!map.containsKey(key)) {
            return null;
        }

        int i = map.get(key);
        Node<K, V> x = arr.get(i);
        // 1. 最后一个元素 e 和第 i 个元素 x 换位置
        Node<K, V> e = arr.get(arr.getSize() - 1);
        arr.set(i, e);
        arr.set(arr.getSize() - 1, x);

        // 2. 修改 map 中 e.key 对应的索引
        map.put(e.key, i);

        // 3. 在数组中 removeLast
        arr.remove();

        // 4. 在 map 中 remove x.key
        map.remove(x.key);

        return x.val;
    }

    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    private static class Node<K, V> {
        K key;
        V val;

        Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }
}
