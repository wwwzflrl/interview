package com.interview.interview.collection.stack;

import lombok.Getter;

/**
 * push    O(1)
 * pop     O(1)
 * peek    O(1)
 * @param <T>
 */
public class MyArrayStack<T> implements MyStack<T> {
    @Getter
    private int size;

    private T[] data;

    private final static int INIT_CAP = 1;

    @SuppressWarnings("unchecked")
    public MyArrayStack() {
        data = (T[]) new Object[INIT_CAP];
        size = 0;
    }

    @Override
    public void push(T t) {
        if (size == data.length) {
            resize(size * 2);
        }
        data[size] = t;
        size += 1;
    }

    @Override
    public T pop() {
        if (size == data.length / 4) {
            resize(data.length / 2);
        }

        T deletedVal = data[size - 1];
        data[size - 1] = null;

        size -= 1;

        return  deletedVal;
    }

    @Override
    public T peek() {
        if (size <= 0) return null;
        return data[size - 1];
    }

    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        if (size > capacity) return;
        T[] temp = (T[]) new Object[capacity];
        for (int i = 0; i < size; i += 1) {
            temp[i] = data[i];
        }
        data = temp;
    }
}
