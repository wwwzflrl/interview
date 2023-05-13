package com.interview.interview.collection.queue;

public interface MyMonotonicQueue<T extends Comparable<T>> extends MyQueue<T> {
    T max();

    T min();
}
