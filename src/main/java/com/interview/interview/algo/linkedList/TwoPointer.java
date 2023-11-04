package com.interview.interview.algo.linkedList;

import lombok.Builder;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * 1、合并两个有序链表
 * 2、合并 k 个有序链表
 * 3、链表的分解
 * 4、寻找单链表的倒数第 k 个节点
 * 5、寻找单链表的中点
 * 6、判断单链表是否包含环并找出环起点
 * 7、判断两个单链表是否相交并找出交点
 * 8、删除排序的全部的重复元素。
 * 9、删除非排序的全部的重复元素。
 *
 */
public class TwoPointer {
    @Builder
    private class ListNode {
        int val = 0;

        ListNode prev = null;

        ListNode next = null;
    }

    /**
     *
     * 合并两个有序列表
     */
    public ListNode mergeTwo(ListNode l1, ListNode l2) {
        ListNode dummy = ListNode.builder().val(-1).build();
        ListNode p = dummy, p1 = l1, p2 = l2;

        while (p1 != null && p2 != null) {
            // 比较 p1 和 p2 两个指针
            // 将值较小的的节点接到 p 指针
            if (p1.val > p2.val) {
                p.next = p2;
                p2 = p2.next;
            } else {
                p.next = p1;
                p1 = p1.next;
            }
            // p 指针不断前进
            p = p.next;
        }

        if (p1 != null) {
            p.next = p1;
        }

        if (p2 != null) {
            p.next = p2;
        }

        return dummy.next;
    }


    /**
     * 合并k个有序列表
     * PriorityQueue, 推出
     */
    public ListNode mergeTwo(ListNode[] lists) {
        if (lists.length == 0) return null;
        // 虚拟头结点
        ListNode dummy = ListNode.builder().val(-1).build();
        ListNode p = dummy;

        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, (a , b) -> a.val - b.val);
        for (ListNode head: lists) {
            if (head != null) {
                pq.add(head);
            }
        }

        while (!pq.isEmpty()) {
            // 获取最小节点，接到结果链表中
            ListNode node = pq.poll();
            p.next = node;
            if (node.next != null) {
                pq.add(node.next);
            }
            // p 指针不断前进
            p = p.next;
        }
        return dummy.next;
    }


    /**
     * 分解成两个，根据x比大小
     */
    ListNode partition(ListNode head, int x) {
        // 存放小于 x 的链表的虚拟头结点
        ListNode dummy1 = ListNode.builder().val(-1).build();
        // 存放大于等于 x 的链表的虚拟头结点
        ListNode dummy2 = ListNode.builder().val(-1).build();
        // p1, p2 指针负责生成结果链表
        ListNode p1 = dummy1, p2 = dummy2;
        // p 负责遍历原链表，类似合并两个有序链表的逻辑
        // 这里是将一个链表分解成两个链表
        ListNode p = head;
        while (p != null) {
            if (p.val >= x) {
                p2.next = p;
                p2 = p2.next;
            } else {
                p1.next = p;
                p1 = p1.next;
            }
            // 不能直接让 p 指针前进，
            // p = p.next
            // 断开原链表中的每个节点的 next 指针
            ListNode temp = p.next;
            p.next = null;
            p = temp;
        }
        // 连接两个链表
        p1.next = dummy2.next;

        return dummy1.next;
    }


    /**
     *  返回链表的倒数第 k 个节点
     */
    ListNode findLastKNode(ListNode head, int k) {
        ListNode p1 = head;
        // p1 先走 k 步
        for (int i = 0; i < k; i++) {
            p1 = p1.next;
        }
        ListNode p2 = head;
        // p1 和 p2 同时走 n - k 步
        while (p1 != null) {
            p2 = p2.next;
            p1 = p1.next;
        }
        // p2 现在指向第 n - k + 1 个节点，即倒数第 k 个节点
        return p2;
    }

    /**
     * 每当慢指针 slow 前进一步，快指针 fast 就前进两步，这样，当 fast 走到链表末尾时，slow 就指向了链表中点。
     */
    ListNode middleNode(ListNode head) {
        // 快慢指针初始化指向 head
        ListNode slow = head, fast = head;
        // 快指针走到末尾时停止
        while (fast != null && fast.next != null) {
            // 慢指针走一步，快指针走两步
            slow = slow.next;
            fast = fast.next.next;
        }
        // 慢指针指向中点
        return slow;
    }

    /**
     * 是否有循环
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast, slow;
        fast = slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }
        // 上面的代码类似 hasCycle 函数
        if (fast == null || fast.next == null) {
            // fast 遇到空指针说明没有环
            return null;
        }

        // 重新指向头结点
        slow = head;

        /**
         * 可以看到，当快慢指针相遇时，让其中任一个指针指向头节点，然后让它俩以相同速度前进，再次相遇时所在的节点位置就是环开始的位置。
         * 为什么要这样呢？这里简单说一下其中的原理。
         * 我们假设快慢指针相遇时，慢指针 slow 走了 k 步，那么快指针 fast 一定走了 2k 步：
         * 假设相遇点距环的起点的距离为 m，那么结合上图的 slow 指针，环的起点距头结点 head 的距离为 k - m，也就是说如果从 head 前进 k - m 步就能到达环起点。
         * 巧的是，如果从相遇点继续前进 k - m 步，也恰好到达环起点。因为结合上图的 fast 指针，从相遇点开始走k步可以转回到相遇点，那走 k - m 步肯定就走到环起点了：
         */
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }


    /**
     * 两条链表是否相交，
     * 连接2个表，看是否有环，
     * 当没有相交的时候都是null
     */
    ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // p1 指向 A 链表头结点，p2 指向 B 链表头结点
        ListNode p1 = headA, p2 = headB;
        while (p1 != p2) {
            // p1 走一步，如果走到 A 链表末尾，转到 B 链表
            if (p1 == null) p1 = headB;
            else            p1 = p1.next;
            // p2 走一步，如果走到 B 链表末尾，转到 A 链表
            if (p2 == null) p2 = headA;
            else            p2 = p2.next;
        }
        return p1;
    }

    /**
     * 删除排序后全部的重复元素。
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = ListNode.builder().val(-1).build();
        ListNode p = dummy, q = head;
        while (q != null) {
            if (q.next != null && q.val == q.next.val){
                // 发现重复节点，跳过这些重复节点
                while (q.next != null && q.val == q.next.val) {
                    q = q.next;
                }
                q = q.next;
                // 此时 q 跳过了这一段重复元素
                if (q == null) {
                    p.next = null;
                }
                // 不过下一段元素也可能重复，等下一轮 while 循环判断
            } else {
                // 不是重复节点，接到 dummy 后面
                p.next = q;
                p = p.next;
                q = q.next;
            }
        }
        return dummy.next;
    }

    /**
     * 删除非排序的全部的重复元素。
     * loop2 遍，第一次遍历统计，第二次移除
     */
    public ListNode deleteDuplicatesUnsorted(ListNode head) {
        HashMap<Integer, Integer> count = new HashMap<>();
        // 先遍历一遍链表，记录每个值出现的次数
        ListNode p = head;
        while (p != null) {
            count.put(p.val, count.getOrDefault(p.val, 0) + 1);
            p = p.next;
        }
        // 虚拟头结点（哨兵节点），存放结果链表
        ListNode dummy = ListNode.builder().val(-1).build();
        dummy.next = head;
        // 再遍历一遍节点，把重复出现的节点剔除
        p = dummy;
        while (p != null) {
            // unique 指针负责寻找不重复的节点
            ListNode unique = p.next;
            while (unique != null && count.get(unique.val) > 1) {
                // 跳过重复节点，直到找到不重复的节点
                unique = unique.next;
            }
            // 接入不重复的节点或尾部空指针
            p.next = unique;
            // p 前进，继续寻找不重复节点
            p = p.next;
        }
        return dummy.next;
    }
}
