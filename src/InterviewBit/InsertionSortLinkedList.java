/*
Sort a linked list using insertion sort.

We have explained Insertion Sort at Slide 7 of Arrays

Insertion Sort Wiki has some details on Insertion Sort as well.

Example :

Input : 1 -> 3 -> 2

Return 1 -> 2 -> 3
 */


public class InsertionSortLinkedList {

	//O(n ^ 2 ) time complexity and O(1) space complexity
	public static ListNode insertionSortList(ListNode root) {
		if( root == null ) return root;

		ListNode element = root;


		while( element != null ){
			int curr = element.val;
			ListNode traverse = root;
			
			while( traverse != null && traverse.val < curr && traverse != element ){
				traverse = traverse.next;
			}
			
			if( traverse != null && traverse != element ){
				swapValues( traverse, element, curr );
			}

			element = element.next;
		}
		
		return root;
    }
    
    public static void swapValues(ListNode traverse, ListNode element, int key){
		
		ListNode temp = traverse;
		int nextVal = temp.val;

		while( temp != element){
			
			if( temp.next == null ){
				break;
			}
			int auxiliary = nextVal;
			nextVal = temp.next.val;
			temp.next.val = auxiliary;
			
			temp = temp.next;
		}
		
		traverse.val = key;
	}
	public static void main (String[] args) {
		
		ListNode lst = new ListNode(1);
		lst.next = new ListNode(4);
		lst.next.next = new ListNode(2);
		lst.next.next.next = new ListNode(5);
		lst.next.next.next.next = new ListNode(0);
		lst.next.next.next.next.next = new ListNode(1);
		ListNode res = insertionSortList( lst );

		while ( res != null ){
			System.out.println(res.val);
			res = res.next;
		}
	}
	
	 static class ListNode {
		public int val;
		public ListNode next;
		ListNode(int x) { val = x; next = null; }
 
  }
}

