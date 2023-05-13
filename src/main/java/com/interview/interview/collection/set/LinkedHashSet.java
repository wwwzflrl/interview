package com.interview.interview.collection.set;

import com.interview.interview.collection.map.MyLinkedHashMap;

import java.util.Iterator;

public class LinkedHashSet<K> {
    private static final Object PRESENT = new Object();
    private MyLinkedHashMap<K, Object> map = new MyLinkedHashMap<K, Object>();

    public boolean add(K k) {
        return map.put(k, PRESENT) == null;
    }

    public boolean remove(K k) {
        return map.remove(k) == PRESENT;
    }

    public boolean contains(K k) {
        return map.containsKey(k);
    }

    public Iterator<K> iterator() {
        return map.keys().iterator();
    }

}
