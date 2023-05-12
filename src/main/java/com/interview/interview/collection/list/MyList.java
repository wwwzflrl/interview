package com.interview.interview.collection.list;

public interface MyList<T> {
    void add(T t);

    void add(int index, T t);

    T remove();

    T remove(int index);

    T get(int index);

    T set(int index, T t);

    int getSize();
}
