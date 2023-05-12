package com.interview.interview.collection.list;

import lombok.Getter;

import java.util.Iterator;

/**
 *  My Array List have 8 main function:
 *  1.   Add Last                O(1)
 *  2.   Add random index        O(N)
 *  3.   Remove Last             O(1)
 *  4.   Remove random index     O(N)
 *  5.   Get random index        O(N)
 *  6.   Update random index     O(N)
 *
 * @param <T>
 */
public class MyLinkedList<T> implements Iterable<T>, MyList<T> {
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

    @Override
    public void add(T t) {
        Node<T> created = new Node<>(t);
        Node<T> temp = tail.prev;

        temp.next = created;
        created.prev = temp;

        created.next = tail;
        tail.prev = created;

        size += 1;
    }

    @Override
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

    @Override
    public T remove() {
        Node<T> x = tail.prev;
        Node<T> temp = tail.prev.prev;

        tail.prev = temp;
        temp.next = tail;

        /**
         * Important do not forget to set to null
         */
        x.prev = null;
        x.next = null;

        size -= 1;
        return x.val;
    }

    @Override
    public T remove(int index) {
        Node<T> x = getNode(index);
        Node<T> prev = x.prev;
        Node<T> next = x.next;

        prev.next = next;
        next.prev = prev;

        /**
         * Important do not forget to set to null
         */
        x.prev = null;
        x.next = null;

        size -= 1;
        return x.val;
    }

    @Override
    public T get(int index) {
        Node<T> p = getNode(index);
        return p.val;
    }

    @Override
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
        return new Iterator<>() {
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
