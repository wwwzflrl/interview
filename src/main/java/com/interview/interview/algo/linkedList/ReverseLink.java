package com.interview.interview.algo.linkedList;

import lombok.Builder;

/**
 * 反转链表 1. 递归 2. 迭代
 *
 * <p>1. 递归反转单链表 2. 递归反转(0,K)链表 3. 递归反转(M,N)链表
 * <p>1. 迭代反转单链表 2. 迭代反转(M,N)链表
 */
public class ReverseLink {
  @Builder
  private class ListNode {
    int val = 0;

    ListNode prev = null;

    ListNode next = null;
  }

  /***************************** recursion ********************************/
  class Recursion {
    /**
     * 递归反转单链表 1. 思是如果链表为空或者只有一个节点的时候，反转结果就是它自己，直接返回即可。 2. 当链表递归反转之后，新的头结点是 last，而之前的 head
     * 变成了最后一个节点，别忘了链表的末尾要指向 null：
     *
     * <p>1->2->3->4->null 1 -> reverse (2->3->4->null) 1 -> (2<-3<-4)
     */
    ListNode reverse(ListNode head) {
      if (head == null || head.next == null) {
        return head;
      }
      ListNode last = reverse(head.next);

      head.next.next = head;

      head.next = null;

      return last;
    }

    /**
     * 反转以 head 为起点的 n 个节点，返回新的头结点 1、base case 变为 n == 1，反转一个元素，就是它本身，同时要记录后驱节点。 2、刚才我们直接把 head.next
     * 设置为 null，因为整个链表反转后原来的 head 变成了整个链表的最后一个节点。 但现在 head 节点在递归反转之后不一定是最后一个节点了，所以要记录后驱 successor（第
     * n + 1 个节点），反转之后将 head 连接上。
     */
    ListNode successor = null; //

    ListNode reverseN(ListNode head, int n) {
      if (n == 1) {
        // 记录第 n + 1 个节点
        successor = head.next;
        return head;
      }
      // 以 head.next 为起点，需要反转前 n - 1 个节点
      ListNode last = reverseN(head.next, n - 1);

      head.next.next = head;
      // 让反转之后的 head 节点和后面的节点连起来
      head.next = successor;
      return last;
    }

    /**
     * m == 1，就相当于反转链表开头的 n 个元素嘛，也就是reverseN 如果 m != 1 怎么办？ 如果我们把 head 的索引视为 1，那么我们是想从第 m 个元素开始反转对吧；
     * 如果把 head.next 的索引视为 1 呢？那么相对于 head.next，反转的区间应该是从第 m - 1 个元素开始的；那么对于 head.next.next 呢……
     */
    ListNode reverseBetween(ListNode head, int m, int n) {
      // base case
      if (m == 1) {
        return reverseN(head, n);
      }
      // 前进到反转的起点触发 base case
      head.next = reverseBetween(head.next, m - 1, n - 1);
      return head;
    }
  }

  /***************************** 迭代 ********************************/
  class Iterate {
    // 反转以 a 为头结点的链表
    ListNode reverse(ListNode a) {
      ListNode pre, cur, nxt;
      pre = null;
      cur = a;
      nxt = a;
      while (cur != null) {
        nxt = cur.next;
        // 逐个结点反转
        cur.next = pre;
        // 更新指针位置
        pre = cur;
        cur = nxt;
      }
      // 返回反转后的头结点
      return pre;
    }

    ListNode reverse(ListNode a, ListNode b) {
      ListNode pre, cur, nxt;
      pre = null; cur = a; nxt = a;
      // while 终止的条件改一下就行了
      while (cur != b) {
        nxt = cur.next;
        cur.next = pre;
        pre = cur;
        cur = nxt;
      }
      // 返回反转后的头结点
      return pre;
    }
  }
}
