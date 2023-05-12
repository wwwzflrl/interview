package com.interview.interview.collection;

import lombok.Getter;

import java.util.Iterator;

/**
 *  My Array List have 8 main function:
 *  Implemented by array
 *  1.   Add Last                O(1)
 *  2.   Add First               O(1)
 *  3.   Add random index        O(N)
 *  4.   Remove Last             O(1)
 *  5.   Remove First            O(1)
 *  6.   Remove random index     O(N)
 *  7.   Get random index        O(N)
 *  8.   Update random index     O(N)
 *
 * @param <T>
 */
public class MyLinkedList<T> implements Iterable<T> {
    final private Node<T> head;
    
    final private Node<T> tail;

    @Getter
    private int size;

    private static class Node<T> {
        T val;
        Node<T> next;
        Node<T> prev;

        Node(T val) {
            this.val = val;
        }
    }

    public MyLinkedList() {
        head = new Node<>(null);
        tail = new Node<>(null);
        head.next = tail;
        tail.prev = head;
        this.size = 0;
    }

    public void addLast(T t) {
        Node<T> created = new Node<>(t);
        Node<T> temp = tail.prev;

        temp.next = created;
        created.prev = temp;

        created.next = tail;
        tail.prev = created;

        size += 1;
    }


    public void addFirst(T t) {
        Node<T> created = new Node<>(t);
        Node<T> temp = head.next;

        created.next = temp;
        temp.prev = created;

        head.next = created;
        created.prev = head;

        size += 1;
    }

    public void add(int index, T t) {
        Node<T> p = getNode(index);
        Node<T> temp = p.prev;

        Node<T> created = new Node<>(t);

        created.next = p;
        p.prev = created;

        temp.next = created;
        created.prev = temp;

        size += 1;
    }

    public T removeFirst() {
        Node<T> x = head.next;
        Node<T> temp = x.next;

        head.next = temp;
        temp.prev = head;

        x.prev = null;
        x.next = null;

        size -= 1;
        return x.val;
    }

    public T removeLast() {
        Node<T> x = tail.prev;
        Node<T> temp = tail.prev.prev;

        tail.prev = temp;
        temp.next = tail;

        x.prev = null;
        x.next = null;

        size -= 1;
        return x.val;
    }

    public T remove(int index) {
        Node<T> x = getNode(index);
        Node<T> prev = x.prev;
        Node<T> next = x.next;

        prev.next = next;
        next.prev = prev;

        x.prev = null;
        x.next = null;

        size -= 1;
        return x.val;
    }

    public T get(int index) {
        Node<T> p = getNode(index);
        return p.val;
    }

    public T set(int index, T val) {
        Node<T> p = getNode(index);

        T oldVal = p.val;
        p.val = val;

        return oldVal;
    }

    private Node<T> getNode(int index) {
        int half = size / 2;
        if (index < half) {
            Node<T> p = head.next;
            for (int i = 0; i < index; i += 1) {
                p = p.next;
            }
            return p;
        }

        Node<T> p = tail.prev;
        int afterIndex = size - 1 - index;
        for (int i = 0; i < afterIndex; i += 1) {
            p = p.prev;
        }
        return p;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> p = head.next;
            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public T next() {
                T val = p.val;
                p = p.next;
                return val;
            }
        };
    }
}
