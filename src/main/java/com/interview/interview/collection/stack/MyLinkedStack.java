package com.interview.interview.collection.stack;

import com.interview.interview.collection.list.MyLinkedList;

/**
 * push   O(1)
 * pop    O(1)
 * peek   O(1)
 * @param <T>
 */
public class MyLinkedStack<T> implements MyStack<T> {
    private final MyLinkedList<T> myLinkedList = new MyLinkedList<>();

    @Override
    public void push(T t) {
        myLinkedList.add(t);
    }

    @Override
    public T pop() {
        return myLinkedList.remove();
    }

    @Override
    public T peek() {
        return myLinkedList.get(myLinkedList.getSize() - 1);
    }

    @Override
    public int getSize() { return myLinkedList.getSize(); }
}
