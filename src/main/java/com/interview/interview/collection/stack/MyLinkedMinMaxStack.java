package com.interview.interview.collection.stack;

public class MyLinkedMinMaxStack<T extends Comparable<T>> implements MyMinMaxStack<T> {
    private final MyStack<T> stack = new MyLinkedStack<>();

    private final MyStack<T> maxStack = new MyLinkedStack<>();

    private final MyStack<T> minStack = new MyLinkedStack<>();


    @Override
    public T max() {
        return maxStack.peek();
    }

    @Override
    public T min() {
        return minStack.peek();
    }

    @Override
    public void push(T t) {
        stack.push(t);
        if (t.compareTo(minStack.peek()) <= 0) {
            minStack.push(t);
        }
        if (t.compareTo(maxStack.peek()) >= 0) {
            maxStack.push(t);
        }
    }

    @Override
    public T pop() {
        T t = stack.pop();
        if (maxStack.peek().equals(t)) {
            maxStack.pop();
        }
        if (minStack.peek().equals(t)) {
            minStack.pop();
        }
        return  t;
    }

    @Override
    public T peek() {
        return stack.peek();
    }

    @Override
    public int getSize() {
        return stack.getSize();
    }
}
