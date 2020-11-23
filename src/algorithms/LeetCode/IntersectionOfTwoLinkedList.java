package algorithms.LeetCode;

import java.util.HashMap;

class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
  }

public class IntersectionOfTwoLinkedList {

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        if( headA == null || headB == null){
            return null;
        }
        HashMap<ListNode,Integer> map = new HashMap<>();

        map.put(headA, 1);

        while( headA.next != null){
            headA = headA.next;
            map.put( headA, 1);
        }

        if( map.get(headB) != null ){
            return headB;
        }

        while ( headB.next != null ){
            headB = headB.next;

            if( map.containsKey( headB )){
                return headB;
            }
        }

        return null;
    }
    public static void main(String[] args) {
        ListNode lst1 = new ListNode(8);

        ListNode lst2 = new ListNode(4);
        lst2.next = lst1;

        System.out.println( getIntersectionNode( lst1, lst2));



    }
}
