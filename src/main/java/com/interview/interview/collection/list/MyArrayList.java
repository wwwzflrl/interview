package com.interview.interview.collection.list;

import lombok.Getter;

import java.util.Iterator;

/**
 * My Array List have 8 main function:
 * Implemented by array
 *  1.   Add Last                O(1)
 *  2.   Add random index        O(N)
 *  3.   Remove Last             O(1)
 *  4.   Remove random index     O(N)
 *  5.   Get random index        O(1)
 *  6.   Update random index     O(1)
 * @param <T>
 */
public class MyArrayList<T> implements Iterable<T>, MyList<T> {
    private T[] data;

    @Getter
    private int size;

    private static final int INIT_CAP = 1;

    public MyArrayList() {
        this(INIT_CAP);
    }

    @SuppressWarnings("unchecked")
    public MyArrayList(int capacity) {
        data = (T[]) new Object[capacity];
        size = 0;
    }

    @Override
    public void add(T t) {
            if (size == data.length) {
                resize(size * 2);
            }
            data[size] = t;
            size += 1;
    }

    @Override
    public void add(int index, T t) {
        if (size == data.length) {
            resize(data.length * 2);
        }

        /**
         * Important
         * Need move index to index + 1
         */
        for (int i = size; i > index; i -= 1) {
            data[i] = data[i - 1];
        }
        data[index] = t;
        size += 1;
    }

    @Override
    public T remove() {
        if (size == data.length / 4) {
            resize(data.length / 2);
        }

        T deletedVal = data[size - 1];

        data[size - 1] = null;
        size -= 1;
        return deletedVal;
    }

    @Override
    public T remove(int index) {
        if (size == data.length / 4) {
            resize(data.length / 2);
        }

        T deletedVal = data[index];

        /**
         * Important
         * Need move index + 1 to index
         */
        for (int i = index; i < size - 1; i += 1) {
            data[i] = data[i + 1];
        }
        data[size - 1] = null;
        size--;

        return deletedVal;
    }

    @Override
    public T get(int index) {
        return data[index];
    }

    @Override
    public T set(int index, T element) {
        T oldVal = data[index];
        data[index] = element;
        return oldVal;
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int idx = 0;
            @Override
            public boolean hasNext() {
                return idx < size;
            }

            @Override
            public T next() {
                idx += 1;
                return data[idx];
            }
        };
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
