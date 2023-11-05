package com.interview.interview.algo.tree;

import java.util.LinkedList;
import java.util.List;

/**
 * 区间内计数，求和，
 * 有区间排序的需求
 */
public class mergeSort {

  /**
   * 给你一个整数数组 nums ，按要求返回一个新数组 counts 。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
   *
   * 示例 1：
   *
   * 输入：nums = [5,2,6,1]
   * 输出：[2,1,1,0]
   * 解释：
   * 5 的右侧有 2 个更小的元素 (2 和 1)
   * 2 的右侧仅有 1 个更小的元素 (1)
   * 6 的右侧有 1 个更小的元素 (1)
   * 1 的右侧有 0 个更小的元素
   */
  class Solution {
        private class Pair {
            int val, id;
            Pair(int val, int id) {
                // 记录数组的元素值
                this.val = val;
                // 记录元素在数组中的原始索引
                this.id = id;
            }
        }

        // 归并排序所用的辅助数组
        private Pair[] temp;
        // 记录每个元素后面比自己小的元素个数
        private int[] count;

        // 主函数
        public List<Integer> countSmaller(int[] nums) {
            int n = nums.length;
            count = new int[n];
            temp = new Pair[n];
            Pair[] arr = new Pair[n];
            // 记录元素原始的索引位置，以便在 count 数组中更新结果
            for (int i = 0; i < n; i++)
                arr[i] = new Pair(nums[i], i);

            // 执行归并排序，本题结果被记录在 count 数组中
            sort(arr, 0, n - 1);

            List<Integer> res = new LinkedList<>();
            for (int c : count) res.add(c);
            return res;
        }

        // 归并排序
        private void sort(Pair[] arr, int lo, int hi) {
            if (lo == hi) return;
            int mid = lo + (hi - lo) / 2;
            sort(arr, lo, mid);
            sort(arr, mid + 1, hi);
            merge(arr, lo, mid, hi);
        }

        // 合并两个有序数组
        private void merge(Pair[] arr, int lo, int mid, int hi) {
            for (int i = lo; i <= hi; i++) {
                temp[i] = arr[i];
            }

            int i = lo, j = mid + 1;
            for (int p = lo; p <= hi; p++) {
                if (i == mid + 1) {
                    arr[p] = temp[j++];
                } else if (j == hi + 1) {
                    arr[p] = temp[i++];
                    // 更新 count 数组
                    count[arr[p].id] += j - mid - 1;
                } else if (temp[i].val > temp[j].val) {
                    arr[p] = temp[j++];
                } else {
                    arr[p] = temp[i++];
                    // 更新 count 数组
                    count[arr[p].id] += j - mid - 1;
                }
            }
        }
    }

  /**
   * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
   *
   * 你需要返回给定数组中的重要翻转对的数量。
   *
   * 示例 1:
   *
   * 输入: [1,3,2,3,1]
   * 输出: 2
   * 示例 2:
   *
   * 输入: [2,4,3,5,1]
   * 输出: 3
   */
  class Solution2 {
        public int reversePairs(int[] nums) {
            // 执行归并排序
            sort(nums);
            return count;
        }

        private int[] temp;

        public void sort(int[] nums) {
            temp = new int[nums.length];
            sort(nums, 0, nums.length - 1);
        }

        // 归并排序
        private void sort(int[] nums, int lo, int hi) {
            if (lo == hi) {
                return;
            }
            int mid = lo + (hi - lo) / 2;
            sort(nums, lo, mid);
            sort(nums, mid + 1, hi);
            merge(nums, lo, mid, hi);
        }

        // 记录「翻转对」的个数
        private int count = 0;

        private void merge(int[] nums, int lo, int mid, int hi) {
            for (int i = lo; i <= hi; i++) {
                temp[i] = nums[i];
            }

            // 进行效率优化，维护左闭右开区间 [mid+1, end) 中的元素乘 2 小于 nums[i]
            // 为什么 end 是开区间？因为这样的话可以保证初始区间 [mid+1, mid+1) 是一个空区间
            int end = mid + 1;
            for (int i = lo; i <= mid; i++) {
                // nums 中的元素可能较大，乘 2 可能溢出，所以转化成 long
                while (end <= hi && (long)nums[i] > (long)nums[end] * 2) {
                    end++;
                }
                count += end - (mid + 1);
            }

            // 数组双指针技巧，合并两个有序数组
            int i = lo, j = mid + 1;
            for (int p = lo; p <= hi; p++) {
                if (i == mid + 1) {
                    nums[p] = temp[j++];
                } else if (j == hi + 1) {
                    nums[p] = temp[i++];
                } else if (temp[i] > temp[j]) {
                    nums[p] = temp[j++];
                } else {
                    nums[p] = temp[i++];
                }
            }
        }
    }

  /**
   * 给你一个整数数组 nums 以及两个整数 lower 和 upper 。求数组中，值位于范围 [lower, upper] （包含 lower 和 upper）之内的 区间和的个数 。
   *
   * 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
   *
   * 示例 1：
   * 输入：nums = [-2,5,-1], lower = -2, upper = 2
   * 输出：3
   * 解释：存在三个区间：[0,0]、[2,2] 和 [0,2] ，对应的区间和分别是：-2 、-1 、2 。
   * 示例 2：
   *
   * 输入：nums = [0], lower = 0, upper = 0
   * 输出：1
   * 提示：
   *
   * 1 <= nums.length <= 105
   * -231 <= nums[i] <= 231 - 1
   * -105 <= lower <= upper <= 105
   * 题目数据保证答案是一个 32 位 的整数
   */
  class Solution3 {
        private int lower, upper;

        public int countRangeSum(int[] nums, int lower, int upper) {
            this.lower = lower;
            this.upper = upper;
            // 构建前缀和数组，注意 int 可能溢出，用 long 存储
            long[] preSum = new long[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                preSum[i + 1] = (long)nums[i] + preSum[i];
            }
            // 对前缀和数组进行归并排序
            sort(preSum);
            return count;
        }

        private long[] temp;

        public void sort(long[] nums) {
            temp = new long[nums.length];
            sort(nums, 0, nums.length - 1);
        }

        private void sort(long[] nums, int lo, int hi) {
            if (lo == hi) {
                return;
            }
            int mid = lo + (hi - lo) / 2;
            sort(nums, lo, mid);
            sort(nums, mid + 1, hi);
            merge(nums, lo, mid, hi);
        }

        private int count = 0;

        private void merge(long[] nums, int lo, int mid, int hi) {
            for (int i = lo; i <= hi; i++) {
                temp[i] = nums[i];
            }

            // 在合并有序数组之前加点私货（这段代码会超时）
            // for (int i = lo; i <= mid; i++) {
            //     for (int j = mid + 1; j <= hi; k++) {
            //         // 寻找符合条件的 nums[j]
            //         long delta = nums[j] - nums[i];
            //         if (delta <= upper && delta >= lower) {
            //             count++;
            //         }
            //     }
            // }

            // 进行效率优化
            // 维护左闭右开区间 [start, end) 中的元素和 nums[i] 的差在 [lower, upper] 中
            int start = mid + 1, end = mid + 1;
            for (int i = lo; i <= mid; i++) {
                // 如果 nums[i] 对应的区间是 [start, end)，
                // 那么 nums[i+1] 对应的区间一定会整体右移，类似滑动窗口
                while (start <= hi && nums[start] - nums[i] < lower) {
                    start++;
                }
                while (end <= hi && nums[end] - nums[i] <= upper) {
                    end++;
                }
                count += end - start;
            }

            // 数组双指针技巧，合并两个有序数组
            int i = lo, j = mid + 1;
            for (int p = lo; p <= hi; p++) {
                if (i == mid + 1) {
                    nums[p] = temp[j++];
                } else if (j == hi + 1) {
                    nums[p] = temp[i++];
                } else if (temp[i] > temp[j]) {
                    nums[p] = temp[j++];
                } else {
                    nums[p] = temp[i++];
                }
            }
        }
    }
}
