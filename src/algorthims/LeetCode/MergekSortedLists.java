import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Example:

Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6
* **/

//(n1, n2) -> n1.val - n2.val this version is much more efficient than comparator.comparingInt
public class MergekSortedLists {

	//time complexity O( nk ), where k is the number of linked lists as we can merge the two sorted linked list in O(n) time
	//space complexity O(1)
	public static ListNode mergeKLists1(ListNode[] lists) {
        
        int len = lists.length;
        if( len == 0 ){
			return null;
		}
		
        ListNode res = lists[0];
        
        for( int i = 1; i<len; i++ ){
			ListNode curr = lists[i];
			
			res = sort( res, curr );
		}
        
        return res;
        
    }

//	//more efficient version
	public static ListNode sort( ListNode lst1, ListNode lst2 ){
		ListNode temp = null;


		//sett up the pointer for the first use
		if( lst1 == null ){
			return lst2;
		}else if( lst2 == null ){
			return lst1;
		}else if( lst1.val < lst2.val ){
			temp = new ListNode( lst1.val );
			lst1 = lst1.next;
		}else{
			temp = new ListNode( lst2.val );
			lst2 = lst2.next;
		}

		ListNode pointer = temp;

		//sorting along
		while( lst1 != null && lst2 != null ){

			if( lst1.val < lst2.val ){
				temp.next = new ListNode( lst1.val );
				temp = temp.next;
				lst1 = lst1.next;
			}else{
				temp.next = new ListNode( lst2.val);
				temp = temp.next;
				lst2 = lst2.next;
			}
		}

		//remaining left
		while( lst1 != null ){
			temp.next = new ListNode( lst1.val );
			temp = temp.next;
			lst1 = lst1.next;
		}

		//remaining right
		while( lst2 != null ){
			temp.next = new ListNode( lst2.val );
			temp = temp.next;
			lst2 = lst2.next;
		}

		return pointer;
	}

	public static void main (String[] args) {

		ListNode root = new ListNode(1);
		root.next = new ListNode(4);
		root.next.next = new ListNode(5);

		ListNode root2 = new ListNode(1);
		root2.next = new ListNode(3);
		root2.next.next = new ListNode(4);

		ListNode root3 = new ListNode(2);
		root3.next = new ListNode(6);

		ListNode[] lst = new ListNode[3];
		lst[0] = root;
		lst[1] = root2;
		lst[2] = root3;



		ListNode res = mergeKLists4( lst );

		while ( res != null ){
			System.out.println(res.val);
			res = res.next;
		}

	}

	///using priorityQueue
	//Time complexity O(N logK ) where k is the number of linked lists
	public static ListNode mergeKLists2(ListNode[] lists) {

		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		
		ListNode res = new ListNode(0);
		ListNode pointer = res;

		//adding all the values
		for( ListNode lst: lists ){
			
			while( lst != null ){
				minHeap.add( lst.val );
				lst = lst.next;
			}
		}
		
		while( !minHeap.isEmpty() ){
			res.next = new ListNode( minHeap.remove() );
			res = res.next;

		}
		
		return pointer.next;

	}

	///using priorityQueue
	//Time complexity O(N logK ) where k is the number of linked lists
	//more faster than upper algorithm
	public static ListNode mergeKLists3(ListNode[] lists) {

		PriorityQueue<ListNode> minHeap = new PriorityQueue<>((n1, n2) -> n1.val - n2.val);



		//adding all the values
		for( ListNode lst: lists ){
			if( lst != null ){
				minHeap.add( lst );
			}

		}

		ListNode res = new ListNode(0);
		ListNode pointer = res;

		while( !minHeap.isEmpty() ){
			ListNode curr = minHeap.poll();
			res.next = curr ;
			res = res.next;

			//we need to check if next exist
			if( res.next != null ){
				minHeap.add( res.next );
			}
		}

		return pointer.next;

	}

	//Merge with divide and conquer
	//O(n logk ) time complexity and O(1) space complexity
	public static ListNode mergeKLists4(ListNode[] lists) {

		int len = lists.length;
		if (len == 0) {
			return null;
		}
		
		return mergeSort( lists, 0, len - 1 );
		
	}

	private static ListNode mergeSort(ListNode[] lists, int low, int high) {
		if( low == high ){
			return lists[low];
		}
		if ( low > high ){
			return null;
		}
		int mid = low + ( high - low )/2;
		
		ListNode lst1 = mergeSort( lists, low, mid );
		ListNode lst2 = mergeSort( lists, mid + 1, high );
		
		return merge( lst1, lst2 );
	}

	//efficient merging
	private static ListNode merge( ListNode lst1, ListNode lst2) {
		if( lst1 == null ) return lst2;
		if( lst2 == null ) return lst1;

		ListNode curr;

		if( lst1.val < lst2.val ){
			curr = lst1;
			curr.next = merge( lst1.next, lst2);
		}else{
			curr = lst2;
			curr.next = merge( lst1, lst2.next );
		}

		return curr;
	}

	public static class ListNode {
	      int val;
	      ListNode next;
	      ListNode() {}
	      ListNode(int val) { this.val = val; }
	      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	  }


}

