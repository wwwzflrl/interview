package com.interview.interview.collection.queue;

import lombok.Getter;

public class MyArrayQueue<T> implements MyQueue<T> {
    @Getter
    private int size;

    private T[] data;

    private final static int INIT_CAP = 1;

    private int first;

    private int last;

    @SuppressWarnings("unchecked")
    public MyArrayQueue(int capacity) {
        size = 0;
        data = (T[]) new Object[capacity];

        first = 0;
        last = 0;
    }

    public MyArrayQueue() {
        this(INIT_CAP);
    }

    @Override
    public void offer(T t) {
        if (size == data.length) {
            resize(size * 2);
        }
        data[last] = t;
        last += 1;

        // Important
        if (last == data.length) {
            last = 0;
        }
        size += 1;
    }

    @Override
    public T poll() {
        if (size == data.length / 4) {
            resize(data.length / 2);
        }

        T val = data[first];
        data[first] = null;
        first += 1;
        if (first == data.length) {
            first = 0;
        }

        size -= 1;
        return val;
    }

    @Override
    public T peek() {
        return data[first];
    }

    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        if (size > capacity) return;
        T[] temp = (T[]) new Object[capacity];

        // Important
        for (int i = 0; i < size; i += 1) {
            temp[i] = data[(first + i) % data.length];
        }
        first = 0;
        last = size;

        data = temp;
    }
}
