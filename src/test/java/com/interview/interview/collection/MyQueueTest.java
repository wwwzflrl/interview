package com.interview.interview.collection;

import com.interview.interview.collection.heap.MyBinaryHeap;
import com.interview.interview.collection.queue.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class MyQueueTest {
    @Test
    public void arrayQueue() {
        MyQueue<Integer> myArrayQueue = new MyArrayQueue<>();

        // add items
        for (int i = 0; i <= 20; i += 4) {
            myArrayQueue.offer(i);
        }

        int expected = 0;
        while (myArrayQueue.getSize() > 0) {
            Assertions.assertEquals(myArrayQueue.peek(), expected);
            Assertions.assertEquals(myArrayQueue.poll(), expected);
            expected += 4;
        }

    }

    @Test
    public void linkedQueue() {
        MyQueue<Integer> myLinkedQueue = new MyLinkedQueue<>();

        // add items
        for (int i = 0; i <= 20; i += 4) {
            myLinkedQueue.offer(i);
        }

        int expected = 0;
        while (myLinkedQueue.getSize() > 0) {
            Assertions.assertEquals(myLinkedQueue.peek(), expected);
            Assertions.assertEquals(myLinkedQueue.poll(), expected);
            expected += 4;
        }

    }

    @Test
    public void arrayDeque() {
        MyDeque<Integer> myArrayDeque = new MyArrayDeque<>();

        // add items
        for (int i = 0; i <= 20; i += 4) {
            myArrayDeque.offerFirst(i);
            myArrayDeque.offerLast(i + 2);
        }

        int expected = 20;
        while (myArrayDeque.getSize() > 0) {
            Assertions.assertEquals(myArrayDeque.peekFirst(), expected);
            Assertions.assertEquals(myArrayDeque.pollFirst(), expected);
            Assertions.assertEquals(myArrayDeque.peekLast(), expected + 2);
            Assertions.assertEquals(myArrayDeque.pollLast(), expected + 2);
            expected -= 4;
        }

    }

    @Test
    public void linkedDeque() {
        MyDeque<Integer> myLinkedDeque = new MyLinkedDeque<>();

        // add items
        for (int i = 0; i <= 20; i += 4) {
            myLinkedDeque.offerFirst(i);
            myLinkedDeque.offerLast(i + 2);
        }

        int expected = 20;
        while (myLinkedDeque.getSize() > 0) {
            Assertions.assertEquals(myLinkedDeque.peekFirst(), expected);
            Assertions.assertEquals(myLinkedDeque.pollFirst(), expected);
            Assertions.assertEquals(myLinkedDeque.peekLast(), expected + 2);
            Assertions.assertEquals(myLinkedDeque.pollLast(), expected + 2);
            expected -= 4;
        }


    }

    @Test
    public void myPriorityQueue() {
        MyQueue<Integer> myPriorityQueue = new MyPriorityQueue<>();

        int[] values = {1, 15, 23,4, 25, 24, 24, 5, 4};
        Arrays.stream(values).forEach((i) -> {
            myPriorityQueue.offer(i);
        });

        Assertions.assertEquals(myPriorityQueue.poll(), 1);
        Assertions.assertEquals(myPriorityQueue.poll(), 4);
        Assertions.assertEquals(myPriorityQueue.poll(), 4);
        Assertions.assertEquals(myPriorityQueue.poll(), 5);
        Assertions.assertEquals(myPriorityQueue.poll(), 15);
        Assertions.assertEquals(myPriorityQueue.poll(), 23);
        Assertions.assertEquals(myPriorityQueue.poll(), 24);
        Assertions.assertEquals(myPriorityQueue.poll(), 24);
        Assertions.assertEquals(myPriorityQueue.poll(), 25);
    }

}
