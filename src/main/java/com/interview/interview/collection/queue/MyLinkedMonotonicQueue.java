package com.interview.interview.collection.queue;

import java.util.LinkedList;

public class MyLinkedMonotonicQueue<T extends Comparable<T>> implements MyMonotonicQueue<T> {
    private final LinkedList<T> queue = new LinkedList<>();

    private final LinkedList<T> maxQueue = new LinkedList<>();

    private final LinkedList<T> minQueue = new LinkedList<>();

    @Override
    public void offer(T t) {
        queue.offer(t);

        // handle max queue
        while (!maxQueue.isEmpty() && maxQueue.getLast().compareTo(t) < 0 ) {
            maxQueue.pollLast();
        }
        maxQueue.offerLast(t);

        // handle min queue
        while (!minQueue.isEmpty() && minQueue.getLast().compareTo(t) > 0 ) {
            minQueue.pollLast();
        }
        minQueue.offerLast(t);
    }

    @Override
    public T poll() {
        T t = queue.poll();

        if (t == maxQueue.peekFirst()) {
            maxQueue.pollFirst();
        }

        if (t == minQueue.peekFirst()) {
            minQueue.pollFirst();
        }

        return t;
    }

    @Override
    public T peek() {
        return queue.peek();
    }

    @Override
    public int getSize() {
        return queue.size();
    }

    @Override
    public T max() {
        return maxQueue.peekFirst();
    }

    @Override
    public T min() {
        return minQueue.peekFirst();
    }
}
