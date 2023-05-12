package com.interview.interview.collection.queue;

import com.interview.interview.collection.list.MyLinkedList;

public class MyLinkedDeque<T> implements MyDeque<T> {
    private final MyLinkedList<T> myLinkedList = new MyLinkedList<>();

    @Override
    public void offerFirst(T t) {
        myLinkedList.add(0, t);
    }

    @Override
    public void offerLast(T t) {
        myLinkedList.add(t);
    }

    @Override
    public T pollFirst() {
        return myLinkedList.remove(0);
    }

    @Override
    public T pollLast() {
        return myLinkedList.remove();
    }

    @Override
    public T peekFirst() {
        return myLinkedList.get(0);
    }

    @Override
    public T peekLast() {
        return myLinkedList.get(myLinkedList.getSize() - 1);
    }

    @Override
    public int getSize() {
        return myLinkedList.getSize();
    }
}
