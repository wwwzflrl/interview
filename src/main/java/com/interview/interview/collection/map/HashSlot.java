package com.interview.interview.collection.map;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HashSlot<K, V> {
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
