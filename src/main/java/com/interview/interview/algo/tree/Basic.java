package com.interview.interview.algo.tree;

import java.util.LinkedList;
import java.util.List;

/**
 *  1、是否可以通过遍历一遍二叉树得到答案？
 *     如果可以，用一个 traverse 函数配合外部变量来实现，这叫「遍历」的思维模式。
 *      void traverse(TreeNode root) {
 *         if (root == null) {
 *             return;
 *         }
 *         // 前序位置
 *         traverse(root.left);
 *         // 中序位置
 *         traverse(root.right);
 *         // 后序位置
 *     }
 *  2、是否可以定义一个递归函数，通过子问题（子树）的答案推导出原问题的答案？
 *     如果可以，写出这个递归函数的定义，并充分利用这个函数的返回值，这叫「分解问题」的思维模式。
 *      void backtrack(TreeNode root) {
 *         if (root == null) {
 *             return;
 *         }
 *         // 前序位置
 *         backtrack(root.left);
 *         // 中序位置
 *         backtrack(root.right);
 *         // 后序位置
 *     }
 *  如果单独抽出一个二叉树节点，它需要做什么事情？需要在什么时候（前/中/后序位置）做？
 *  其他的节点不用你操心，递归函数会帮你在所有节点上执行相同的操作。
 *  前序 / 中序 / 后序
 *
 *  1. 快速排序
 *  2. 并归排序
 *  3. 前序排序常规写法，自顶向下，默认用法
 *  4. 后序特殊之处，自底向上。当年的数据需要遍历完子树才能知道，放在后续
 *     二叉树
 *  5，中序类似bst，binary search 常用
 */
public class Basic {

    /* 基本的 N 叉树节点 */
    class TreeNode {
        int val;
        TreeNode left;

        TreeNode right;
    }

    // 1. 快速排序
    void quickSort(int[] nums, int lo, int hi) {
        /****** 前序遍历位置 ******/
        // 通过交换元素构建分界点 p
        int p = 0;
        // p = partition(nums, lo, hi); do partition
        /************************/

        quickSort(nums, lo, p - 1);
        quickSort(nums, p + 1, hi);
    }

    //  2. 并归排序
    void mergeSort(int[] nums, int lo, int hi) {
        int mid = (lo + hi) / 2;
        // 排序 nums[lo..mid]
        mergeSort(nums, lo, mid);
        // 排序 nums[mid+1..hi]
        mergeSort(nums, mid + 1, hi);

        /****** 后序位置 ******/
        // 合并 nums[lo..mid] 和 nums[mid+1..hi]
        // merge(nums, lo, mid, hi);
        /*********************/
    }

    // 记录最大深度
    class MaxDepth {
        // 记录最大深度
        int res = 0;
        // 记录遍历到的节点的深度
        int depth = 0;

        // 主函数
        int maxDepth(TreeNode root) {
            traverse(root);
            return res;
        }

        // 二叉树遍历框架
        void traverse(TreeNode root) {
            if (root == null) {
                return;
            }
            // 前序位置
            depth++;
            if (root.left == null && root.right == null) {
                // 到达叶子节点，更新最大深度
                res = Math.max(res, depth);
            }
            traverse(root.left);
            traverse(root.right);
            // 后序位置
            depth--;
        }
    }

    // 转成前序数组
    class PreOrder {
        List<Integer> res = new LinkedList<>();

        // 返回前序遍历结果
        List<Integer> preorderTraverse1(TreeNode root) {
            traverse(root);
            return res;
        }

        // 二叉树遍历函数
        void traverse(TreeNode root) {
            if (root == null) {
                return;
            }
            // 前序位置
            res.add(root.val);
            traverse(root.left);
            traverse(root.right);
        }

        List<Integer> preorderTraverse2(TreeNode root) {
            List<Integer> res = new LinkedList<>();
            if (root == null) {
                return res;
            }
            // 前序遍历的结果，root.val 在第一个
            res.add(root.val);
            // 利用函数定义，后面接着左子树的前序遍历结果
            res.addAll(preorderTraverse2(root.left));
            // 利用函数定义，最后接着右子树的前序遍历结果
            res.addAll(preorderTraverse2(root.right));

            return res;
        }
    }

    // 求树的最长直径，，需要遍历后的信息，所以后续
    class PreorderSolution {
        // 记录最大直径的长度
        int maxDiameter = 0;

        public int diameterOfBinaryTree(TreeNode root) {
            maxDepth(root);
            return maxDiameter;
        }

        int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int leftMax = maxDepth(root.left);
            int rightMax = maxDepth(root.right);
            // 后序位置，顺便计算最大直径
            int myDiameter = leftMax + rightMax;
            maxDiameter = Math.max(maxDiameter, myDiameter);

            return 1 + Math.max(leftMax, rightMax);
        }
    }
}
