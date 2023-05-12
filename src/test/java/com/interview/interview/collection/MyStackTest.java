package com.interview.interview.collection;

import com.interview.interview.collection.stack.MyArrayStack;
import com.interview.interview.collection.stack.MyLinkedStack;
import com.interview.interview.collection.stack.MyStack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyStackTest {
    @Test
    public void arrayStack() {
        MyStack<Integer> myArrayStack = new MyArrayStack<>();

        // add items
        for (int i = 0; i <= 20; i += 4) {
            myArrayStack.push(i);
        }

        int expected = 20;
        while (myArrayStack.getSize() > 0) {
            Assertions.assertEquals(myArrayStack.peek(), expected);
            Assertions.assertEquals(myArrayStack.pop(), expected);
            expected -= 4;
        }

    }

    @Test
    public void linkedStack() {
        MyStack<Integer> myLinkedStack = new MyLinkedStack<>();

        // add items
        for (int i = 0; i <= 20; i += 4) {
            myLinkedStack.push(i);
        }

        int expected = 20;
        while (myLinkedStack.getSize() > 0) {
            Assertions.assertEquals(myLinkedStack.peek(), expected);
            Assertions.assertEquals(myLinkedStack.pop(), expected);
            expected -= 4;
        }

    }
}
