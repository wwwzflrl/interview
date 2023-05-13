package com.interview.interview.collection.map;

import java.util.LinkedList;

public class MyLinkedHashMap<K, V> {
    private MyHashMap<K, Node<K, V>> map = new MyHashMap<>();

    private final Node<K, V> head, tail;

    public MyLinkedHashMap() {
        head = new Node<>(null, null);
        tail = new Node<>(null, null);
        head.next = tail;
        tail.prev = head;
    }

    public V put(K key, V val) {
        // 若为新插入的节点，则同时插入链表和 map
        if (!map.containsKey(key)) {
            // 插入新的 Node
            Node<K, V> x = new Node<>(key, val);
            addLastNode(x);
            map.put(key, x);
            return null;
        }
        // 若存在，则替换之前的 val
        Node<K, V> x = map.get(key);
        V oldVal = x.val;
        x.val = val;

        return oldVal;
    }

    public V remove(K key) {
        // 若 key 本不存在，直接返回
        if (!map.containsKey(key)) {
            return null;
        }
        // 若 key 存在，则需要在链表中也删除
        Node<K,V> x = map.remove(key);
        removeNode(x);
        return x.val;
    }

    public Iterable<K> keys() {
        LinkedList<K> keyList = new LinkedList<>();
        for (Node<K, V> p = head.next; p != tail; p = p.next) {
            keyList.addLast(p.key);
        }
        return keyList;
    }

    public boolean containsKey(K key) {
        return map.containsKey(key);
    }


    private void addLastNode(Node<K, V> x) {
        Node<K, V> temp = tail.prev;
        x.next = tail;
        x.prev = temp;

        temp.next = x;
        tail.prev = x;
    }

    private void removeNode(Node<K, V> x) {
        Node<K, V> prev = x.prev;
        Node<K, V> next = x.next;

        prev.next = next;
        next.prev = prev;

        x.next = x.prev = null;
    }


    private static class Node<K, V> {
        K key;
        V val;
        Node<K, V> next, prev;

        Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

}
