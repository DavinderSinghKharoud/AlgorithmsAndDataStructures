package LeetCode;

import java.util.*;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * <p>
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
 * <p>
 * You may not alter the values in the list's nodes, only nodes themselves may be changed.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [2,1,4,3,5]
 * Example 2:
 * <p>
 * <p>
 * Input: head = [1,2,3,4,5], k = 3
 * Output: [3,2,1,4,5]
 * Example 3:
 * <p>
 * Input: head = [1,2,3,4,5], k = 1
 * Output: [1,2,3,4,5]
 * Example 4:
 * <p>
 * Input: head = [1], k = 1
 * Output: [1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is in the range sz.
 * 1 <= sz <= 5000
 * 0 <= Node.val <= 1000
 * 1 <= k <= sz
 */
public class ReverseNodesKGroup {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode res = new ReverseNodesKGroup().reverseKGroup(node1, 3);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }

    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) return head;
        ListNode root = new ListNode(-1);
        ListNode prev = root;

        ListNode temp = head;
        while (temp != null) {
            int count = 1;
            ListNode curr = temp;
            while (temp != null && count < k) {
                temp = temp.next;
                count++;
            }
            if (count != k) {
                return root.next;
            }
            ListNode next = (temp != null) ? temp.next : null;
            List<ListNode> reverse = getReverse(curr, temp);

            prev.next = reverse.get(0);
            if (reverse.get(1) != null) reverse.get(1).next = next;
            prev = reverse.get(1);
            temp = next;
        }
        return root.next;
    }

    List<ListNode> getReverse(ListNode start, ListNode end) {
        if (end == null) {
            return Arrays.asList(start, null);
        }
        ListNode prev = start;
        ListNode next = (start != null) ? start.next : null;
        prev.next = null;
        while (prev != end && next != null) {
            ListNode nextNext = next.next;
            next.next = prev;

            prev = next;
            next = nextNext;
        }
        return Arrays.asList(end, start);
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
