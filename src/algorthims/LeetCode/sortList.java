//package algorthims.LeetCode;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.

Example 1:

Input: 
4->2->1->3
Output: 1->2->3->4
**/


 
 
public class sortList{


	static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
    public static ListNode sortList(ListNode head) {
       if( head == null || head.next == null) return head;
       
       ListNode slow = head;
       ListNode fast = head;
       
       while( fast.next != null && fast.next.next != null ){
	   slow = slow.next;
	   fast = fast.next.next;
       }
       
       fast = slow.next;
       slow.next = null;
       
       ListNode left_list = sortList( head);
       ListNode right_list = sortList( fast );
       
       return merge( left_list, right_list);
    }
    
    public static ListNode merge( ListNode left, ListNode right ){
	
	ListNode head = new ListNode( 0 );
	ListNode curr = head;
	
	while( left != null && right != null ){
	    if( left.val <= right.val ){
		curr.next = left;
		left = left.next;
	    }else{
		curr.next = new ListNode(right.val);
		right = right.next;
	    }

		curr = curr.next;
	}
		if(left != null)
			curr.next = left;
		else if(right != null)
			curr.next = right;

		return head.next;

	}
    
    public static void main(String[] a){
	ListNode lst = new ListNode(-1);
	lst.next = new ListNode( 5 );
	lst.next.next = new ListNode(3);
	lst.next.next.next = new ListNode(4);
	lst.next.next.next.next = new ListNode(0);
	
	sortList( lst );
	
	while( lst != null ){
	    System.out.println( lst.val );
	    lst = lst.next;
	}
	
    }
    
}
