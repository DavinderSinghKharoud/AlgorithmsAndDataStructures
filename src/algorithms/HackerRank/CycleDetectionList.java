package algorithms.HackerRank;

public class CycleDetectionList {

    static boolean hasCycle(SinglyLinkedListNode head) {


        if (head == null) return false;

        SinglyLinkedListNode slow = head;
        SinglyLinkedListNode fast = (head.next != null) ? head.next.next : null;

        while (fast != null && fast.next != null) {
            if (slow == fast) return true;
            slow = slow.next;
            fast = fast.next.next;
        }

        return false;

    }

    public static void main(String[] args) {

        SinglyLinkedListNode lst = new SinglyLinkedListNode(1);
        //lst.next = new SinglyLinkedListNode(2);
        //lst.next.next = new SinglyLinkedListNode(3);
        //lst.next.next.next = lst;

        System.out.println( hasCycle(lst));
    }

    static class SinglyLinkedListNode {
        int data;
        SinglyLinkedListNode next;

        public SinglyLinkedListNode(int data) {
            this.data = data;
        }
    }
}
