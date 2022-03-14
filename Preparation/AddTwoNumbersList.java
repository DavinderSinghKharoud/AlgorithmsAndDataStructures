package Preparation;

/**
 * You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 *
 * Output: 7 -> 0 -> 8
 *
 *     342 + 465 = 807
 * Make sure there are no trailing zeros in the output list
 *
 * So, 7 -> 0 -> 8 -> 0 is not a valid response even though the value is still 807.
 */
public class AddTwoNumbersList {

    class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode addTwoNumbers(ListNode lst1, ListNode lst2) {
        int carry = 0;
        ListNode ans = new ListNode(-1);
        ListNode temp = ans;
        ListNode start1 = lst1, start2 = lst2;
        while (start1 != null && start2 != null) {
            int curr = carry + start1.val + start2.val;
            int val = curr % 10;
            carry = curr / 10;
            ans.next = new ListNode(val);
            ans = ans.next;
            start1 = start1.next;
            start2 = start2.next;
        }
        if (start1 != null) {
            while (start1 != null) {
                int curr = carry + start1.val;
                int val = curr % 10;
                carry = curr / 10;
                ans.next = new ListNode(val);
                ans = ans.next;
                start1 = start1.next;
            }
        }

        if (start2 != null) {
            while (start2 != null) {
                int curr = carry + start2.val;
                int val = curr % 10;
                carry = curr / 10;
                ans.next = new ListNode(val);
                ans = ans.next;
                start2 = start2.next;
            }
        }
        if (carry != 0) {
            ans.next = new ListNode(carry);
        }
        return temp.next;
    }

}
