package com.java.interview.algorithm;

public class ReverseList {

    public static void main(String[] args) {
        ListNode node5 = new ListNode(5, null);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);

        ListNode node = reverseListRecursion(node1);
        System.out.println(node);
    }

    /**
     * 迭代法反转链表
     * @return
     */
    public static ListNode reverseListIterate(ListNode head) {
        ListNode v_prev = null, v_next;
        ListNode cur = head;
        while (cur != null) {
            // 将当前结点的 next 结点保存到 v_next 中
            v_next = cur.next;
            // 将当前结点的 next 指向 v_prev
            cur.next = v_prev;
            // 将当前结点保存到 v_prev 中，以备后用
            v_prev = cur;
            // 将当前结点移动到下一个结点处，即之前保存下来的 v_next 结点
            cur = v_next;
        }

        return v_prev;
    }

    /**
     * 递归法反转链表
     * @return
     */
    public static ListNode reverseListRecursion(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = reverseListRecursion(head.next);
        // 当前结点的下一个结点的 next 指针指向当前节点。
        head.next.next = head;
        // 当前结点的 next 指针指向 NULL，避免成环
        head.next = null;

        return node;
    }

    /**
     * 结点类 ListNode
     */
    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
