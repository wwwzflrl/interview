package com.interview.interview.collection.set;

import com.interview.interview.collection.map.MyHashMap;

public class MyHashSet<K> {
    private static final Object PRESENT = new Object();

    private final MyHashMap<K, Object> map = new MyHashMap<>();

    public boolean add(K k) {
        return map.put(k, PRESENT) == null;
    }

    public boolean remove(K k) {
        return map.remove(k) == PRESENT;
    }

    public boolean contains(K k) {
        return map.containsKey(k);
    }
}
