package com.interview.interview.collection.queue;

public interface MyDeque<T> {
    void offerFirst(T t);

    void offerLast(T t);

    T pollFirst();

    T pollLast();

    T peekFirst();

    T peekLast();

    int getSize();
}
