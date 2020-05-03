package algorthims.LeetCode;

/**
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 *
 * To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.
 *
 * Note: Do not modify the linked list.
 *
 *
 *
 * Example 1:
 *
 * Input: head = [3,2,0,-4], pos = 1
 * Output: tail connects to node index 1
 * Explanation: There is a cycle in the linked list, where tail connects to the second node.
 */
public class LinkedListCycleII {
	
	public static ListNode detectCycle(ListNode head) {
	        
	     ListNode slow = head;
	     ListNode fast = head;

	     //we can also do like  while( fast != null && fast.next != null ), because fast is moving more faster than slow
	     while( slow != null && fast != null && fast.next != null ){
			 
			slow = slow.next;
			fast = fast.next.next;
			
			if( slow == fast ){
				while( head != slow ){	
					slow = slow.next;
					head = head.next;
				}
				
				return head;
			}
		 }
		 
		 return null;
    }
    
	public static void main (String[] args) {
		ListNode lst = new ListNode(3);
        lst.next = new ListNode(2);
        lst.next.next = new ListNode(0);
        lst.next.next.next = new ListNode(-4);
        lst.next.next.next.next = lst.next;

        ListNode zero = new ListNode(1);
        zero.next = new ListNode(2);
        System.out.println( detectCycle( zero ).val);
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

