package com.interview.interview.collection.heap;

import lombok.Getter;

import java.util.Comparator;

/**
 * Use Array to represent Complete Binary tree
 * [0, 1, 2, 3, 4, 5, 6, 7]
 *            1
 *       2        3
 *    4    5    6   7
 *
 *    node parent idx   = node idx / 2
 *    node left child   = node idx * 2
 *    node right childe = node idx * 2 + 1
 *
 */
public class MyBinaryHeap<K extends Comparable<K>> {
    @Getter
    private int size = 0;


    private K[] heap;

    private Comparator<K> comparator;

    private static final int INIT_CAP = 1;

    public MyBinaryHeap() {
        heap = (K[]) new Comparable[INIT_CAP];
        comparator = Comparator.naturalOrder();
    }

    public MyBinaryHeap(Comparator<K> comparator) {
        heap = (K[]) new Comparable[INIT_CAP];
        this.comparator = comparator;
    }

    public K peek() {
        if (heap.length < 1) return null;
        return heap[1];
    }

    public void push(K k) {
        if (size >= heap.length - 1) {
            resize(heap.length * 2);
        }

        size += 1;
        heap[size] = k;
        swim(size);
    }

    public K pop() {
        if (size < (heap.length / 2 - 1)) {
            resize(heap.length / 2);
        }

        K min = heap[1];

        heap[1] = heap[size];
        heap[size] = null;
        size -= 1;

        sink(1);
        return min;
    }

    private void swim(int idx) {
        // 如果浮到堆顶，就不能再上浮了
        while (idx > 1 && !lessOrEqual(getParentIdx(idx), idx)) {
            // 如果第 idx 个元素比上层大
            // 将 idx 换上去
            K tmp = heap[idx];
            heap[idx] = heap[getParentIdx(idx)];
            heap[getParentIdx(idx)] = tmp;

            idx = getParentIdx(idx);
        }
    }

    private void sink(int idx) {
        // 如果沉到堆底，就沉不下去了
        while (getLeftChildIdx(idx) <= size) {
            // 找左右节点最小的
            int minChildIdx = getLeftChildIdx(idx);
            if (getRightChildIdx(idx) <= size && lessOrEqual(getRightChildIdx(idx), minChildIdx)) {
                minChildIdx = getRightChildIdx(idx);
            }

            // 如果第 idx 个元素比下层最小的都小，不用沉下去
            if (lessOrEqual(idx, minChildIdx)) break;

            // 如果第 idx 个元素比上层大
            // 将 idx 换上去
            K tmp = heap[idx];
            heap[idx] = heap[minChildIdx];
            heap[minChildIdx] = tmp;

            idx = minChildIdx;
        }
    }

    private boolean lessOrEqual(int i, int j) {
        return comparator.compare(heap[i], heap[j]) <= 0;
    }

    private int getParentIdx(int idx) {
        return idx / 2;
    }

    private int getLeftChildIdx(int idx) {
        return idx * 2;
    }

    private int getRightChildIdx(int idx) {
        return idx * 2 + 1;
    }

    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        if (size > capacity) return;
        K[] temp = (K[]) new Comparable[capacity];
        for (int i = 0; i < size + 1; i += 1) {
            temp[i] = heap[i];
        }
        heap = temp;
    }
}
