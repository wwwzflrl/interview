package com.interview.interview.collection.set;

import com.interview.interview.collection.map.MyArrayHashMap;

public class MyArrayHashSet<K> {
    private static final Object PRESENT = new Object();

    private final MyArrayHashMap<K, Object> map = new MyArrayHashMap<>();

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
