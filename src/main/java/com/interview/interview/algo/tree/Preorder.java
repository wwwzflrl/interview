package com.interview.interview.algo.tree;

public class Preorder {
    /* 基本的 N 叉树节点 */
    class TreeNode {
        int val;
        TreeNode left;

        TreeNode right;
    }

    /**
     * 反转二叉树
     */
    class InvertTree {
        TreeNode invertTree(TreeNode root) {
            // 遍历二叉树，交换每个节点的子节点
            traverse(root);
            return root;
        }

        // 二叉树遍历函数
        void traverse(TreeNode root) {
            if (root == null) {
                return;
            }

            /**** 前序位置 ****/
            // 每一个节点需要做的事就是交换它的左右子节点
            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;

            // 遍历框架，去遍历左右子树的节点
            traverse(root.left);
            traverse(root.right);
        }
    }

  /**
   * 输入 root = [1,2,3,4,5,6,7]
   * 输出：[1,#,2,3,#,4,5,6,7,#]
   *  // 三叉树遍历函数, leetcode 116
   */
  class ConvertTree {
      TreeNode ConvertTree(TreeNode root) {
          // 遍历二叉树，交换每个节点的子节点
          traverse(root.left, root.right);
          return root;
      }

      // width first
      // 三叉树遍历框架
      void traverse(TreeNode node1, TreeNode node2) {
          if (node1 == null || node2 == null) {
              return;
          }
          /**** 前序位置 ****/
          // 将传入的两个节点穿起来
//          node1.next = node2;

          // 连接相同父节点的两个子节点
          traverse(node1.left, node1.right);
          traverse(node2.left, node2.right);
          // 连接跨越父节点的两个子节点
          traverse(node1.right, node2.left);
      }
  }

}
