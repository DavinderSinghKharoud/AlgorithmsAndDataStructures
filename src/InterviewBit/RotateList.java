package InterviewBit;

/**
 * Given a list, rotate the list to the right by k places, where k is non-negative.
 *
 * For example:
 *
 * Given 1->2->3->4->5->NULL and k = 2,
 * return 4->5->1->2->3->NULL.
 */
public class RotateList {

    public static ListNode rotateRight(ListNode root, int n) {
		
		if( n == 0 ) return root;
		int len = getLen(root);
		
		n %= len;
		if( n == 0 ) return root;
		ListNode traverse = root;
		int count = 0;
		
		ListNode res = null;
		while( traverse.next != null ){
			count++;
			
			if( count == len - n ){
				ListNode next = traverse.next;
				traverse.next = null;
				traverse = next;
				res = next;
				continue;
			}
			
			traverse = traverse.next;
		}

		traverse.next = root;
		
		return res;
    }
    
    public static int getLen( ListNode root ){
		ListNode traverse = root;
		
		int len = 0;
		
		while( traverse != null ){
			len++;
			traverse = traverse.next;
		}
		
		return len;
	}
    public static void main(String[] args) {

    	ListNode root = new ListNode(1);
//    	root.next = new ListNode(2);
//    	root.next.next = new ListNode(3);
//		root.next.next.next = new ListNode(4);
//		root.next.next.next.next = new ListNode(5);

		ListNode temp = rotateRight( root, 1);

		while ( temp != null ){
			System.out.println(temp.val);
			temp = temp.next;
		}
    }

     static class ListNode {
      public int val;
      public ListNode next;
      ListNode(int x) { val = x; next = null; }
  }
}
