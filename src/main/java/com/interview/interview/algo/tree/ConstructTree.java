package com.interview.interview.algo.tree;

public class ConstructTree {
    /* 基本的 N 叉树节点 */
    class TreeNode {
        int val;
        TreeNode left;

        TreeNode right;
    }

  /**
   * 输入：nums = [3,2,1,6,0,5]
   * 输出：[6,3,5,null,2,0,null,null,1]
   */
  class ConstructMaximumBinaryTree {
      /* 主函数 */
      TreeNode constructMaximumBinaryTree(int[] nums) {
          return build(nums, 0, nums.length - 1);
      }

      // 定义：将 nums[lo..hi] 构造成符合条件的树，返回根节点
      TreeNode build(int[] nums, int lo, int hi) {
          // base case
          if (lo > hi) {
              return null;
          }

          // 找到数组中的最大值和对应的索引
          int index = -1, maxVal = Integer.MIN_VALUE;
          for (int i = lo; i <= hi; i++) {
              if (maxVal < nums[i]) {
                  index = i;
                  maxVal = nums[i];
              }
          }

          // 先构造出根节点
          TreeNode root = new TreeNode();
          root.val = maxVal;
          // 递归调用构造左右子树
          root.left = build(nums, lo, index - 1);
          root.right = build(nums, index + 1, hi);

          return root;
      }
  }

    /**
     * 前序/中序 遍历构造
     * 前序找root，
     * 中序定义
     */
    class BuildTree {
        /* 主函数 */
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            // 根据函数定义，用 preorder 和 inorder 构造二叉树
            return build(preorder, 0, preorder.length - 1,
                    inorder, 0, inorder.length - 1);
        }

        /*
            build 函数的定义：
            若前序遍历数组为 preorder[preStart..preEnd]，
            中序遍历数组为 inorder[inStart..inEnd]，
            构造二叉树，返回该二叉树的根节点
        */
        TreeNode build(int[] preorder, int preStart, int preEnd,
                       int[] inorder, int inStart, int inEnd) {
            // root 节点对应的值就是前序遍历数组的第一个元素
            int rootVal = preorder[preStart];
            // rootVal 在中序遍历数组中的索引
            int index = 0;
            for (int i = inStart; i <= inEnd; i++) {
                if (inorder[i] == rootVal) {
                    index = i;
                    break;
                }
            }
            int leftSize = index - inStart;

            TreeNode root = new TreeNode();
            root.val = rootVal;

            // 递归构造左右子树
            root.left = build(preorder, preStart + 1, preStart + leftSize,
                    inorder, inStart, index - 1);

            root.right = build(preorder, preStart + leftSize + 1, preEnd,
                    inorder, index + 1, inEnd);
            return root;
        }
    }
}
