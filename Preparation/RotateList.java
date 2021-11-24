package Preparation;

/**
 * Given the head of a linked list, rotate the list to the right by k places.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [4,5,1,2,3]
 * Example 2:
 *
 *
 * Input: head = [0,1,2], k = 4
 * Output: [2,0,1]
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [0, 500].
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 109
 */
public class RotateList {

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return head;
        int len = getLen(head);
        k %= len;
        if (k == 0) return head;

        int reach = len - k;
        ListNode tail = getTail(head);

        int count = 1;
        ListNode temp = head;

        while (count != reach && temp != null) {
            count++;
            temp = temp.next;
        }
        //temp should be the last now
        ListNode ans = temp.next;
        temp.next = null;

        tail.next = head;
        return ans;
    }

    ListNode getTail(ListNode node) {
        ListNode temp = node;
        while (temp.next != null) {
            temp = temp.next;
        }

        return temp;
    }

    int getLen(ListNode node) {
        int len = 0;
        ListNode temp = node;
        while (temp != null) {
            len++;
            temp = temp.next;
        }

        return len;
    }

    public class ListNode {
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
