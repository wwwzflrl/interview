package com.interview.interview.collection.queue;

import com.interview.interview.collection.list.MyLinkedList;

public class MyLinkedQueue<T> implements MyQueue<T> {
    private final MyLinkedList<T> myLinkedList = new MyLinkedList<>();

    @Override
    public void offer(T t) {
        myLinkedList.add(t);
    }

    @Override
    public T poll() {
        return myLinkedList.remove(0);
    }

    @Override
    public T peek() {
        return myLinkedList.get(0);
    }

    @Override
    public int getSize() {
        return myLinkedList.getSize();
    }
}
