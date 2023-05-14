package com.interview.interview.collection.list;

import lombok.Getter;

/**
 * Similar to linked list
 * Use recursive instead of iterator
 * @param <T>
 */
public class MyRecursiveList<T> implements MyList<T> {

    private Node<T> head = new Node<>(null);

    @Getter
    private int size = 0;

    @Override
    public void add(T t) {
        add(head, size, t);
        size += 1;
    }

    @Override
    public void add(int index, T t) {
        add(head, index, t);
        size += 1;
    }

    @Override
    public T remove() {
        Node<T> t = get(head, size);
        remove(head, size);
        size -= 1;
        return t.val;
    }

    @Override
    public T remove(int index) {
        Node<T> t = get(head, index + 1);
        remove(head, index + 1);
        size -= 1;
        return t.val;
    }

    @Override
    public T get(int index) {
        return get(head, index + 1).val;
    }

    @Override
    public T set(int index, T t) {
        Node<T> node = get(head, index);
        T oldVal = node.val;
        node.val = t;
        return oldVal;
    }

    private Node<T> get(Node<T> node, int index) {
        if (index == 0) {
            return node;
        }

        return get(node.next, index - 1);
    }

    private Node<T> add(Node<T> node, int index, T t) {
       if (index == 0) {
           Node<T> created = new Node<>(t);
           created.next = node.next;
           node.next = created;
           return node;
       }

       node.next = add(node.next, index - 1, t);

       return node;
    }

    private Node<T> remove(Node<T> node, int index) {
        if (index == 0) {
            Node<T> nextNode = node.next;
            node.next = null;
           return nextNode;
        }

        node.next = remove(node.next, index - 1);

        return node;
    }

    private static class Node<T> {
        T val;
        Node<T> next;

        Node(T val) {
            this.val = val;
        }
    }
}
