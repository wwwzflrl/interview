package com.interview.interview.collection;

import com.interview.interview.collection.heap.MyBinaryHeap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class MyHeapTest {

    @Test
    public void myMinBinaryHeap() {
        MyBinaryHeap<Integer> myBinaryHeap = new MyBinaryHeap<>();

        int[] values = {1, 15, 23,4, 25, 24, 24, 5, 4};
        Arrays.stream(values).forEach((i) -> {
            myBinaryHeap.push(i);
        });

        Assertions.assertEquals(myBinaryHeap.pop(), 1);
        Assertions.assertEquals(myBinaryHeap.pop(), 4);
        Assertions.assertEquals(myBinaryHeap.pop(), 4);
        Assertions.assertEquals(myBinaryHeap.pop(), 5);
        Assertions.assertEquals(myBinaryHeap.pop(), 15);
        Assertions.assertEquals(myBinaryHeap.pop(), 23);
        Assertions.assertEquals(myBinaryHeap.pop(), 24);
        Assertions.assertEquals(myBinaryHeap.pop(), 24);
        Assertions.assertEquals(myBinaryHeap.pop(), 25);
    }

    @Test
    public void myMaxBinaryHeap() {
        MyBinaryHeap<Integer> myBinaryHeap = new MyBinaryHeap<>((a, b) -> 0 - a.compareTo(b));

        int[] values = {1, 15, 23,4, 25, 24, 24, 5, 4};
        Arrays.stream(values).forEach((i) -> {
            myBinaryHeap.push(i);
        });

        Assertions.assertEquals(myBinaryHeap.pop(), 25);
        Assertions.assertEquals(myBinaryHeap.pop(), 24);
        Assertions.assertEquals(myBinaryHeap.pop(), 24);
        Assertions.assertEquals(myBinaryHeap.pop(), 23);
        Assertions.assertEquals(myBinaryHeap.pop(), 15);
        Assertions.assertEquals(myBinaryHeap.pop(), 5);
        Assertions.assertEquals(myBinaryHeap.pop(), 4);
        Assertions.assertEquals(myBinaryHeap.pop(), 4);
        Assertions.assertEquals(myBinaryHeap.pop(), 1);
    }

}
