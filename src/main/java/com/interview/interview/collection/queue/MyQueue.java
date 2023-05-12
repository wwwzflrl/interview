package com.interview.interview.collection.queue;

public interface MyQueue<T> {
    void offer(T t);

    T poll();

    T peek();

    int getSize();
}
