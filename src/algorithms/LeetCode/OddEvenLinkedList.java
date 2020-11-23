package algorithms.LeetCode;

public class OddEvenLinkedList {

    public static ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;

        while (even != null && even.next != null) {

                odd.next = even.next;
                odd = odd.next;
                even.next = odd.next;
                even = even.next;

        }

        odd.next = evenHead;
        return head;
    }

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        ListNode node = oddEvenList(head);

        while (node != null) {
            System.out.println( node.val);
            node = node.next;
        }

    }
}
