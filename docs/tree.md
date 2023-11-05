二叉树遍历
1. 是否可以通过遍历一遍二叉树得到答案？
   如果可以，用一个 traverse 函数配合外部变量来实现，这叫「遍历」的思维模式。
2. 是否可以定义一个递归函数，通过子问题（子树）的答案推导出原问题的答案？
   如果可以，写出这个递归函数的定义，并充分利用这个函数的返回值，这叫「分解问题」的思维模式

如果单独抽出一个二叉树节点，它需要做什么事情？需要在什么时候（前/中/后序位置）

* 快排 前序
```java
class QuickSort {
    void sort(int[] nums, int lo, int hi) {
        /****** 前序遍历位置 ******/
        // 通过交换元素构建分界点 p
        int p = partition(nums, lo, hi);
        /************************/

        sort(nums, lo, p - 1);
        sort(nums, p + 1, hi);
    }
}
```

* 并归
```java
class MergeSort {
    void sort(int[] nums, int lo, int hi) {
        int mid = (lo + hi) / 2;
        // 排序 nums[lo..mid]
        sort(nums, lo, mid);
        // 排序 nums[mid+1..hi]
        sort(nums, mid + 1, hi);

        /****** 后序位置 ******/
        // 合并 nums[lo..mid] 和 nums[mid+1..hi]
        merge(nums, lo, mid, hi);
        /*********************/
    }
}
```

遍历
```java
class Traverse {
    void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        // 前序位置
        traverse(root.left);
        // 中序位置
        traverse(root.right);
        // 后序位置
    }
}
```

DFS
```java
class DFS {
   // DFS 算法把「做选择」「撤销选择」的逻辑放在 for 循环外面
   void dfs(Node root) {
      if (root == null) return;
      // 做选择
      print("我已经进入节点 %s 啦", root);
      for (Node child : root.children) {
         dfs(child);
      }
      // 撤销选择
      print("我将要离开节点 %s 啦", root);
   }

   // 回溯算法把「做选择」「撤销选择」的逻辑放在 for 循环里面
   void backtrack(Node root) {
      if (root == null) return;
      for (Node child : root.children) {
         // 做选择
         print("我站在节点 %s 到节点 %s 的树枝上", root, child);
         backtrack(child);
         // 撤销选择
         print("我将要离开节点 %s 到节点 %s 的树枝上", child, root);
      }
   }
}
```