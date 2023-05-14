package com.interview.interview.collection.stack;

public interface MyMinMaxStack<T extends Comparable<T>> extends MyStack<T> {
    T max();

    T min();
}
