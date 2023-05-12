package com.interview.interview.collection.stack;

public interface MyStack<T> {
    void push(T t);

    T pop();

    T peek();

    int getSize();
}
