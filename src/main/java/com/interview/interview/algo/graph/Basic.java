package com.interview.interview.algo.graph;


import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

/**
 * 1. 图结构 Graph，本质高级多叉树
 * 2. 一般用 matrix[x][y]
 * 3. 遍历
 */
public class Basic {
    /* 图节点的逻辑结构 */
    class Graph {
        int id;

        Graph[] neighbors;
    }

    /* 基本的 N 叉树节点 */
    class TreeNode {
        int val;
        TreeNode[] children;
    }


    // DFS 算法，关注点在节点
    void traverse(TreeNode root) {
        if (root == null) return;
        System.out.printf("进入节点 %s", root);
        for (TreeNode child : root.children) {
            traverse(child);
        }
        System.out.printf("离开节点 %s", root);
    }

    // 回溯算法，关注点在树枝
    void backtrack(TreeNode root) {
        if (root == null) return;
        for (TreeNode child : root.children) {
            // 做选择
            System.out.printf("从 %s 到 %s", root, child);
            backtrack(child);
            // 撤销选择
            System.out.printf("从 %s 到 %s", child, root);
        }
    }


    /********** 遍历图的经典做法 **************/
    // 记录被遍历过的节点
    boolean[] visited;
    // 记录从起点到当前节点的路径
    boolean[] onPath;

    /* 图遍历框架 */
    void traverse(Graph graph, int s) {
        if (visited[s]) return;
        // 经过节点 s，标记为已遍历
        visited[s] = true;
        // 做选择：标记节点 s 在路径上
        onPath[s] = true;
        for (Graph neighbor : graph.neighbors) {
            traverse(graph, neighbor.id);
        }
        // 撤销选择：节点 s 离开路径
        onPath[s] = false;
    }

  /**
   * 遍历所有节点
   * 比如输入 graph = [[1,2],[3],[3],[]]
   * 实际就是
    *    * [0][1]
    *    * [2][3]
   *
   * 算法应该返回 [[0,1,3],[0,2,3]]
   */
  class Solution {
        // 记录所有路径
        List<List<Integer>> res = new LinkedList<>();

        public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
            // 维护递归过程中经过的路径
            LinkedList<Integer> path = new LinkedList<>();
            traverse(graph, 0, path);
            return res;
        }

        /* 图的遍历框架 */
        void traverse(int[][] graph, int s, LinkedList<Integer> path) {

            // 添加节点 s 到路径
            path.addLast(s);

            int n = graph.length;
            if (s == n - 1) {
                // 到达终点
                res.add(new LinkedList<>(path));
                // 可以在这直接 return，但要 removeLast 正确维护 path
                // path.removeLast();
                // return;
                // 不 return 也可以，因为图中不包含环，不会出现无限递归
            }

            // 递归每个相邻节点
            for (int v : graph[s]) {
                traverse(graph, v, path);
            }

            // 从路径移出节点 s
            path.removeLast();
        }
    }
}
