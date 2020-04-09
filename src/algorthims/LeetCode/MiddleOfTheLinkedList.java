package algorthims.LeetCode;

public class MiddleOfTheLinkedList {
	
	    public static ListNode middleNode(ListNode head) {
		    
		    
		    ListNode slow = head;
		    ListNode fast = head;
		    
		    while( fast != null && fast.next != null ){
			
			slow = slow.next;
			if( fast.next != null ) fast = fast.next.next;
			
		    }
		    
		    return slow; 
        
	    }
	    public static void main (String[] args) {
		
		ListNode lst = new ListNode(1);
		lst.next = new ListNode(2);
		lst.next.next = new ListNode(3);
		lst.next.next.next = new ListNode(4);
		
		System.out.println( middleNode(lst).val );
		    
	    }
	    
	    
	     
	      public static class ListNode {
		  int val;
		  ListNode next;
		  ListNode(int x) { val = x; }
	     }
 
}

