package com.interview.interview.algo.dynamic;

/**
 * 标准解法：
 * void backtrack(int[] candidates, int start, int target, int sum) {
 *     // 回溯算法框架
 *     for (int i = start; i < candidates.length; i++) {
 *         // 选择 candidates[i]
 *         backtrack(candidates, i + 1, target, sum);
 *         // 撤销选择 candidates[i]
 *     }
 * }
 *
 * 可以使用重复元素
 * void backtrack(int[] candidates, int start, int target, int sum) {
 *     // 回溯算法框架
 *     for (int i = start; i < candidates.length; i++) {
 *         // 选择 candidates[i]
 *         backtrack(candidates, i, target, sum);
 *         // 撤销选择 candidates[i]
 *     }
 * }
 *
 */
public class Basic {}
