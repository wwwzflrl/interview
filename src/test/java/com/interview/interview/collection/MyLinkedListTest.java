package com.interview.interview.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyLinkedListTest {

    @Test
    public void canOperateArray() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList();

        // add items
        for (int i = 0; i <= 20; i += 4) {
            myLinkedList.addLast(i);
        }
        Assertions.assertEquals(myLinkedList.getSize(), 6);

        Integer deletedItem = myLinkedList.remove(3);
        Assertions.assertEquals(myLinkedList.getSize(), 5);
        Assertions.assertEquals(deletedItem, 12);

        myLinkedList.add(1, 9);
        Assertions.assertEquals(myLinkedList.getSize(), 6);
        Assertions.assertEquals(myLinkedList.get(1), 9);


        myLinkedList.addFirst(100);
        Assertions.assertEquals(myLinkedList.getSize(), 7);
        Assertions.assertEquals(myLinkedList.get(0), 100);

        Integer deletedItem2 = myLinkedList.removeLast();
        Assertions.assertEquals(myLinkedList.getSize(), 6);
        Assertions.assertEquals(deletedItem2, 20);


        Assertions.assertEquals(myLinkedList.get(0), 100);
        Assertions.assertEquals(myLinkedList.get(1), 0);
        Assertions.assertEquals(myLinkedList.get(2), 9);
        Assertions.assertEquals(myLinkedList.get(3), 4);
        Assertions.assertEquals(myLinkedList.get(4), 8);
        Assertions.assertEquals(myLinkedList.get(5), 16);

    }
}
