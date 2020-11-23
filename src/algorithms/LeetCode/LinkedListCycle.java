package algorithms.LeetCode;

/**
 * Given a linked list, determine if it has a cycle in it.
 *
 * To represent a cycle in the given linked list,
 * we use an integer pos which represents the position (0-indexed)
 * in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.
 *
 *
 */
public class LinkedListCycle {

    public static void main(String[] args) {
        ListNode lst = new ListNode(3);
        lst.next = new ListNode(2);
        lst.next.next = new ListNode(0);
        lst.next.next.next = new ListNode(-4);
        lst.next.next.next.next = lst;

        System.out.println( hasCycle( lst ));
    }
    public static boolean hasCycle(ListNode head) {

        if( head == null || head.next == null){
            return false;
        }

        ListNode slow = head;
        ListNode fast = head.next;

        while ( slow != fast  ){

            if(  fast == null || fast.next == null){
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;

        }
        return true;


    }
}
