package com.interview.interview.collection.map;

public class MyTreeMap<K, V> {
    private TreeNode root = null;

    private class TreeNode {
        K key;
        V val;
        TreeNode left, right;
        // 记录，以该节点为根的 BST 有多少个节点
        int size;

        TreeNode(K key, V val) {
            this.key = key;
            this.val = val;
            this.size = 1;
            left = right = null;
        }
    }
}
