package com.interview.interview.algo.tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * 而后序位置的代码不仅可以获取参数数据，还可以获取到子树通过函数返回值传递回来的数据。
 */
public class Postorder {
    /* 基本的 N 叉树节点 */
    class TreeNode {
        int val;
        TreeNode left;

        TreeNode right;
    }

    class RepeatTree {

        // 记录所有子树
        HashMap<String, Integer> subTrees = new HashMap<>();
        // 记录重复的子树根节点
        LinkedList<TreeNode> res = new LinkedList<>();

        /* 主函数 */
        List<TreeNode> findDuplicateSubtrees(TreeNode root) {
            serialize(root);
            return res;
        }

        /* 辅助函数 */
        String serialize(TreeNode root) {
            if (root == null) {
                return "#";
            }

            // 先算左右子树的序列化结果
            String left = serialize(root.left);
            String right = serialize(root.right);

            String myself = left + "," + right+ "," + root.val;

            int freq = subTrees.getOrDefault(myself, 0);
            // 多次重复也只会被加入结果集一次
            if (freq == 1) {
                res.add(root);
            }
            // 给子树对应的出现次数加一
            subTrees.put(myself, freq + 1);
            return myself;
        }

    }
}
