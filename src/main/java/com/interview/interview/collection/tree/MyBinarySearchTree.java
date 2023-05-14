package com.interview.interview.collection.tree;

import lombok.Getter;

import java.util.LinkedList;
import java.util.Optional;

public class MyBinarySearchTree<K extends Comparable<K>, V> {
    @Getter
    private TreeNode root = null;

    public void put(K key, V val) { root = put(root, key, val); }

    private TreeNode put(TreeNode node, K key, V val) {
        if (node == null) return new TreeNode(key, val);
        int cmp = key.compareTo(node.getKey());
        if (cmp > 0) {
            node.right = put(node.right, key, val);
        } else if (cmp < 0) {
            node.left = put(node.left, key, val);
        } else {
            node.val = val;
        }
        return node;
    }

    public void remove(K key) { root = remove(root, key); }

    private TreeNode remove(TreeNode node, K key) {
        if (node == null) return null;
        int cmp = node.getKey().compareTo(key);
        if (cmp > 0) {
            node.right = remove(node.right, key);
        } else if (cmp < 0) {
            node.left = remove(node.left, key);
        } else {
            // 1. node 是叶子节点，左右子树都是 null
            // 2. node 左右子树有一个非空
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }

            // 3. node 左右子树都是非空的
            // 3.1 得到左子树最大的那个节点的指针 leftMax
            node.key = maxNode(node.left).getKey();
            // 3.2 删除左子树最大的那个节点
            node.left = remove(node.left, node.key);
        }
        return node;
    }

    private TreeNode maxNode(TreeNode p) {
        while (p.right != null) {
            p = p.right;
        }
        return p;
    }

    private TreeNode minNode(TreeNode p) {
        while (p.left != null) {
            p = p.left;
        }
        return p;
    }

    /***** 查 *****/

    // 返回 key 对应的 val，如果 key 不存在，则返回 null
    public V get(K key) {
        TreeNode x = get(root, key);
        if (x == null) return null;
        return x.val;
    }

    // 在以 node 为根的 BST 中查找 key
    private TreeNode get(TreeNode node, K key) {
        if (node == null)  return null;

        int cmp = node.key.compareTo(key);

        // node.key > key
        if (cmp > 0) {
            return get(node.left, key);
        }

        // node.key < key
        if (cmp < 0) {
            return get(node.right, key);
        }

        // node.key == key
        return node;
    }

    // 返回小于等于 key 的最大的键
    public K floorKey(K key) { return floorKey(root, key).key; }

    private TreeNode floorKey(TreeNode node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = node.key.compareTo(key);

        // node.key > key，去左子树找
        if (cmp > 0) {
            return floorKey(node.left, key);
        }

        // node.key < key，去右子树找
        if (cmp < 0) {
            TreeNode x = floorKey(node.right, key);
            if (x == null) {
                return node;
            }
            return x;
        }

        return node;
    }

    // 从小到大返回闭区间 [min, max] 中的键
    public Iterable<K> keys(K min, K max) {
        LinkedList<K> list = new LinkedList<>();
        traverse(root, list, min, max);
        return list;
    }

    // 中序遍历 BST
    private void traverse(TreeNode node, LinkedList<K> list, K min, K max) {
        if (node == null) {
            return;
        }

        int cmpMin = min.compareTo(node.key);
        int cmpMax = max.compareTo(node.key);

        if (cmpMin < 0) {
            // min < node.key
            traverse(node.left, list, min, max);
        }

        // 中序遍历 min <= node.key <= max
        if (cmpMin <= 0 && cmpMax >= 0) {
            list.addLast(node.key);
        }

        if (cmpMax > 0) {
            // max > node.key
            traverse(node.right, list, min, max);
        }
    }


    private class TreeNode {
        @Getter
        K key;
        V val;
        TreeNode left, right;

        TreeNode(K key, V val) {
            this.key = key;
            this.val = val;
            left = right = null;
        }
    }
}
