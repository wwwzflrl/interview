package com.interview.interview.collection;

import com.interview.interview.collection.tree.MyBinarySearchTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyTreeTest {

    @Test
    public void binarySearchTreeTest() {
        MyBinarySearchTree<Integer, Integer> myBinarySearchTree = new MyBinarySearchTree<>();

        myBinarySearchTree.put(14, null);
        myBinarySearchTree.put(1, null);
        myBinarySearchTree.put(16, null);
        myBinarySearchTree.put(7, null);
        myBinarySearchTree.put(23, null);
        myBinarySearchTree.put(24, null);
        myBinarySearchTree.put(15, null);
        myBinarySearchTree.put(2, null);

        Assertions.assertEquals(myBinarySearchTree.keys(5, 16).toString(), "[7, 14, 15, 16]");
        Assertions.assertEquals(myBinarySearchTree.floorKey(17), 16);
        Assertions.assertEquals(myBinarySearchTree.floorKey(2), 2);
    }
}
