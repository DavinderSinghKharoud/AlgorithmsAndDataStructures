package LeetCode.WeeklyContest270;

public class DeleteMiddleNodeLinkedList {
    public static void main(String[] args) {
        DeleteMiddleNodeLinkedList o = new DeleteMiddleNodeLinkedList();
    }

    public ListNode deleteMiddle(ListNode head) {
        if (head == null) return null;
        int len = getLen(head);
        if (len == 1) return null;
        int middle = len / 2;
        ListNode temp = head;
        ListNode prev = null;
        int count = 0;
        while (count != middle && temp != null) {
            count++;
            prev = temp;
            temp = temp.next;
        }
        ListNode next = temp.next;

        temp.next = null;
        if (prev != null)
            prev.next = next;
        return head;
    }


    int getLen(ListNode node) {
        int count = 1;
        while (node.next != null) {
            count++;
            node = node.next;
        }
        return count;
    }

    static public class ListNode {
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
