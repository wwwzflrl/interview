package com.interview.interview.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyArrayListTest {

    @Test
    public void canOperateArray() {
        MyArrayList<Integer> myArrayList = new MyArrayList<>(3);

        // add items
        for (int i = 0; i <= 20; i += 4) {
            myArrayList.addLast(i);
        }
        Assertions.assertEquals(myArrayList.getSize(), 6);

        Integer deletedItem = myArrayList.remove(3);
        Assertions.assertEquals(myArrayList.getSize(), 5);
        Assertions.assertEquals(deletedItem, 12);

        myArrayList.add(1, 9);
        Assertions.assertEquals(myArrayList.getSize(), 6);
        Assertions.assertEquals(myArrayList.get(1), 9);


        myArrayList.addFirst(100);
        Assertions.assertEquals(myArrayList.getSize(), 7);
        Assertions.assertEquals(myArrayList.get(0), 100);

        Integer deletedItem2 = myArrayList.removeLast();
        Assertions.assertEquals(myArrayList.getSize(), 6);
        Assertions.assertEquals(deletedItem2, 20);


        Assertions.assertEquals(myArrayList.get(0), 100);
        Assertions.assertEquals(myArrayList.get(1), 0);
        Assertions.assertEquals(myArrayList.get(2), 9);
        Assertions.assertEquals(myArrayList.get(3), 4);
        Assertions.assertEquals(myArrayList.get(4), 8);
        Assertions.assertEquals(myArrayList.get(5), 16);

    }
}
