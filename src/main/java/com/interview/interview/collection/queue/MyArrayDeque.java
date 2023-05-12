package com.interview.interview.collection.queue;

import lombok.Getter;

public class MyArrayDeque<T> implements MyDeque<T> {
    @Getter
    private int size;

    private T[] data;

    private final static int INIT_CAP = 1;

    private int first;

    private int last;

    @SuppressWarnings("unchecked")
    public MyArrayDeque(int capacity) {
        size = 0;
        data = (T[]) new Object[capacity];

        first = 0;
        last = 0;
    }

    public MyArrayDeque() {
        this(INIT_CAP);
    }

    @Override
    public void offerFirst(T t) {
        if (size == data.length) {
            resize(size * 2);
        }

        /**
         * Important
         * first is included
         * move first
         * Case1 --first----last--
         * Case2 --last----first--
         */
        if (first == 0) {
            first = data.length - 1;
        } else {
            first -= 1;
        }
        data[first] = t;

        size += 1;
    }

    @Override
    public void offerLast(T t) {
        if (size == data.length) {
            resize(size * 2);
        }

        /**
         * Important last is not include
         * insert first
         * Case1 --first----last--
         * Case2 --last----first--
         */
        data[last] = t;
        last += 1;
        if (last == data.length) {
            last = 0;
        }

        size += 1;
    }

    @Override
    public T pollFirst() {
        if (size == data.length / 4) {
            resize(data.length / 2);
        }

        T val = peekFirst();

        data[first] = null;
        first += 1;

        if (first == data.length) {
            first = 0;
        }

        size -= 1;

        return val;
    }

    @Override
    public T pollLast() {
        if (size == data.length / 4) {
            resize(data.length / 2);
        }

        /**
         * Important last is not include
         * move first
         * Case1 --first----last--
         * Case2 --last----first--
         */
        if (last == 0) {
            last = data.length - 1;
        } else {
            last -= 1;
        }
        T val = data[last];
        data[last] = null;
        size -= 1;

        return val;
    }

    @Override
    public T peekFirst() {
        return data[first];
    }

    @Override
    public T peekLast() {
        if (last == 0) return data[data.length - 1];
        return data[last - 1];
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
