package com.interview.interview.algo.linkedList;

import lombok.Builder;

/**
 * 回文
 * 寻找回文串的核心思想是从中心向两端扩展：
 * 因为回文串长度可能为奇数也可能是偶数，长度为奇数时只存在一个中心点，而长度为偶数时存在两个中心点，所以上面这个函数需要传入 l 和 r。
 * 而判断一个字符串是不是回文串就简单很多，不需要考虑奇偶情况
 *
 *  * void traverse(ListNode head) {
 *  *     // 前序遍历代码
 *  *     traverse(head.next);
 *  *     // 后序遍历代码
 *  * }
 */
public class Palindrome {
    @Builder
    private class ListNode {
        int val = 0;

        ListNode prev = null;

        ListNode next = null;
    }

    ListNode left;
    boolean isPalindrome(ListNode head) {
        left = head;
        return traverse(head);
    }

    boolean traverse(ListNode right) {
        if (right == null) return true;
        boolean res = traverse(right.next);
        // 后序遍历代码
        res = res && (right.val == left.val);
        left = left.next;
        return res;
    }

    // 在 s 中寻找以 s[left] 和 s[right] 为中心的最长回文串
    String palindrome(String s, int left, int right) {
        // 防止索引越界
        while (left >= 0 && right < s.length()
                && s.charAt(left) == s.charAt(right)) {
            // 双指针，向两边展开
            left--;
            right++;
        }
        // 返回以 s[left] 和 s[right] 为中心的最长回文串
        return s.substring(left + 1, right);
    }

    boolean isPalindrome(String s) {
        // 一左一右两个指针相向而行
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
