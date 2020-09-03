package com.xldeng.solution1_20;

/**
 * Created on 2020/9/2.
 *
 * @author xldeng
 */
public class Solution19 {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode first = new ListNode(0);
        first.next = head;
        int len = 0;
        ListNode node1 = first;
        ListNode node2 = first;
        for (int i = 0; i <= n; i++) {
            node1 = node1.next;
        }
        while (node1 != null) {
            node1 = node1.next;
            node2 = node2.next;
        }
        node2.next = node2.next.next;
        return first.next;
    }
}
