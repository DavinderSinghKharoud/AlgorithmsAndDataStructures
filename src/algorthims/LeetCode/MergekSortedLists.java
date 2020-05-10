import java.util.ArrayList;
import java.util.List;

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


public class MergekSortedLists {

	//time complexity O( nk ), where k is the number of linked lists as we can merge the two sorted linked list in O(n) time
	//space complexity O(1)
	public static ListNode mergeKLists(ListNode[] lists) {
        
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



		ListNode res = mergeKLists( lst );

		while ( res != null ){
			System.out.println(res.val);
			res = res.next;
		}

	}



	  public static class ListNode {
	      int val;
	      ListNode next;
	      ListNode() {}
	      ListNode(int val) { this.val = val; }
	      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	  }
}

