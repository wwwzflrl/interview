package com.interview.interview.collection;

import com.interview.interview.collection.list.MyArrayList;
import com.interview.interview.collection.list.MyLinkedList;
import com.interview.interview.collection.list.MyList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyListTest {

    @Test
    public void arrayList() {
        MyList<Integer> myArrayList = new MyArrayList<>(3);

        // add items
        for (int i = 0; i <= 20; i += 4) {
            myArrayList.add(i);
        }
        Assertions.assertEquals(myArrayList.getSize(), 6);

        Integer deletedItem = myArrayList.remove(3);
        Assertions.assertEquals(myArrayList.getSize(), 5);
        Assertions.assertEquals(deletedItem, 12);

        myArrayList.add(1, 9);
        Assertions.assertEquals(myArrayList.getSize(), 6);
        Assertions.assertEquals(myArrayList.get(1), 9);


        myArrayList.add(0, 100);
        Assertions.assertEquals(myArrayList.getSize(), 7);
        Assertions.assertEquals(myArrayList.get(0), 100);

        Integer deletedItem2 = myArrayList.remove();
        Assertions.assertEquals(myArrayList.getSize(), 6);
        Assertions.assertEquals(deletedItem2, 20);


        Assertions.assertEquals(myArrayList.get(0), 100);
        Assertions.assertEquals(myArrayList.get(1), 0);
        Assertions.assertEquals(myArrayList.get(2), 9);
        Assertions.assertEquals(myArrayList.get(3), 4);
        Assertions.assertEquals(myArrayList.get(4), 8);
        Assertions.assertEquals(myArrayList.get(5), 16);

    }

    @Test
    public void linkedList() {
        MyList<Integer> myLinkedList = new MyLinkedList<>();

        // add items
        for (int i = 0; i <= 20; i += 4) {
            myLinkedList.add(i);
        }
        Assertions.assertEquals(myLinkedList.getSize(), 6);

        Integer deletedItem = myLinkedList.remove(3);
        Assertions.assertEquals(myLinkedList.getSize(), 5);
        Assertions.assertEquals(deletedItem, 12);

        myLinkedList.add(1, 9);
        Assertions.assertEquals(myLinkedList.getSize(), 6);
        Assertions.assertEquals(myLinkedList.get(1), 9);


        myLinkedList.add(0, 100);
        Assertions.assertEquals(myLinkedList.getSize(), 7);
        Assertions.assertEquals(myLinkedList.get(0), 100);

        Integer deletedItem2 = myLinkedList.remove();
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
