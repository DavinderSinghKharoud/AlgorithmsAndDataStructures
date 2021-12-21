package LeetCode.WeeklyContest267;

import java.util.*;

public class ReverseString {
    public static void main(String[] args) {
        ReverseString o = new ReverseString();
        ListNode one = new ListNode(5);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(6);
        ListNode four = new ListNode(3);
        ListNode ficve = new ListNode(9);
        ListNode six = new ListNode(1);
        ListNode seven = new ListNode(7);
        ListNode eight = new ListNode(3);
        ListNode nine = new ListNode(8);
        ListNode ten = new ListNode(4);
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = ficve;
        ficve.next = six;
        six.next = seven;
        seven.next = eight;
        eight.next = nine;
        nine.next = ten;
        ;
        ListNode res = o.reverseEvenLengthGroups(one);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

    public ListNode reverseEvenLengthGroups(ListNode head) {
        if (head == null)
            return null;
        ListNode temp = head, prev = null;
        int count = 1;
        ArrayDeque<ListNode> stack = new ArrayDeque<>();
        while (temp != null) {
            int curr = 1;
            while (temp != null && curr <= count) {
                curr++;
                stack.addLast(temp);
                temp = temp.next;
                stack.peekLast().next = null;
            }

            if (stack.size() % 2 == 0) {
                while (!stack.isEmpty()) {
                    prev.next = stack.pollLast();
                    prev = prev.next;
                }
            } else {
                while (!stack.isEmpty()) {
                    if (prev != null) {
                        prev.next = stack.pollFirst();
                        prev = prev.next;
                    } else {
                        prev = stack.pollFirst();
                    }

                }
            }
            count++;
        }
        return head;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
