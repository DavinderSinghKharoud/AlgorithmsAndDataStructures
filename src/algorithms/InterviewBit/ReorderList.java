package algorithms.InterviewBit;

public class ReorderList {

	/**
	 * Given a singly linked list
	 *
	 *     L: L0 → L1 → … → Ln-1 → Ln,
	 * reorder it to:
	 *
	 *     L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
	 * You must do this in-place without altering the nodes’ values.
	 *
	 * For example,
	 * Given {1,2,3,4}, reorder it to {1,4,2,3}.
	 * @param lst
	 * @return
	 */
    public static ListNode reorderList(ListNode lst) {

       ListNode slow = lst;
       ListNode fast = lst;
       
       while( fast.next != null && fast.next.next != null ){
		   slow = slow.next;
		   fast = fast.next.next;
	   }
	   
	   
	   ListNode middle = slow.next;
	   slow.next = null;
	   
	   ListNode reverse = reverseList( middle );
	   
	   ListNode res = lst;
	   
	   while( reverse != null ){
		   ListNode temp1 = res.next;
		   ListNode temp2 = reverse.next;

		   res.next = reverse;
		   reverse.next = temp1;

		   res = temp1;
		   reverse = temp2;


	   }

		return lst;
    }
    
    public static ListNode reverseList( ListNode root ){
    	if( root == null ||  root.next == null) return root;
		
		ListNode getBack = reverseList( root.next );
		root.next.next = root;
		root.next = null;
		return getBack;
	}

    public static void main(String[] args) {

		ListNode root = new ListNode(1);
    	root.next = new ListNode(2);
    	root.next.next = new ListNode(3);
		root.next.next.next = new ListNode(4);
		//root.next.next.next.next = new ListNode(5);

		ListNode res = reorderList( root );

		while ( res != null ){
			System.out.println(res.val);
			res = res.next;
		}
    }

    static class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
