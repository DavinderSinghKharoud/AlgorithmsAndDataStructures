package algorthims.LeetCode;


import java.util.HashMap;
import java.util.List;

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
