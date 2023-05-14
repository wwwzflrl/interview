package com.interview.interview.collection.queue;

import com.interview.interview.collection.heap.MyBinaryHeap;

public class MyPriorityQueue<T extends Comparable<T>> implements MyQueue<T> {
    MyBinaryHeap<T> heap = new MyBinaryHeap<>();

    @Override
    public void offer(T t) {
        heap.push(t);
    }

    @Override
    public T poll() {
        return heap.pop();
    }

    @Override
    public T peek() {
        return heap.peek();
    }

    @Override
    public int getSize() {
        return heap.getSize();
    }
}
