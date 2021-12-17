package Preparation;

/**
 * Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * <p>
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.
 * <p>
 * Do not modify the linked list.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [3,2,0,-4], pos = 1
 * Output: tail connects to node index 1
 * Explanation: There is a cycle in the linked list, where tail connects to the second node.
 * Example 2:
 * <p>
 * <p>
 * Input: head = [1,2], pos = 0
 * Output: tail connects to node index 0
 * Explanation: There is a cycle in the linked list, where tail connects to the first node.
 */
public class LinkedListCycleII {
    /**
     * Define two pointers slow and fast. Both start at head node, fast is twice as fast as slow. If it reaches the end it means there is no cycle, otherwise eventually it will eventually catch up to slow pointer somewhere in the cycle.
     * <p>
     * Let the distance from the first node to the the node where cycle begins be FindingThreeDigitEvenNumbers, and let say the slow pointer travels travels FindingThreeDigitEvenNumbers+DeleteMiddleNodeLinkedList.
     * The fast pointer must travel 2A+2B to catch up. The cycle size is N. Full cycle is also how much more fast pointer has traveled
     * than slow pointer at meeting point.
     * <p>
     * FindingThreeDigitEvenNumbers+DeleteMiddleNodeLinkedList+N = 2A+2B
     * N=FindingThreeDigitEvenNumbers+DeleteMiddleNodeLinkedList
     * From our calculation slow pointer traveled exactly full cycle when it meets fast pointer, and
     * since originally it travled FindingThreeDigitEvenNumbers before starting on a cycle, it must travel FindingThreeDigitEvenNumbers to reach the point where cycle
     * begins! We can start another slow pointer at head node, and move both pointers until they meet at the beginning of a cycle.
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        if (head == null || head.next == null) return null;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow.equals(fast)) break;
        }

        if (!slow.equals(fast)) return null;
        System.out.println(slow.val);
        while (!head.equals(slow)) {
            head = head.next;
            slow = slow.next;
        }

        return head;
    }


    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
