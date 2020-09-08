package algorthims.InterviewBit;

/**
 * Merge two sorted linked lists and return it as a new list.
 * The new list should be made by splicing together the nodes of the first two lists, and should also be sorted.
 *
 * For example, given following linked lists :
 *
 *   5 -> 8 -> 20
 *   4 -> 11 -> 15
 * The merged list should be :
 *
 * 4 -> 5 -> 8 -> 11 -> 15 -> 20
 */
public class MergeTwoSortedLists {

    public static ListNode mergeTwoLists(ListNode lst1, ListNode lst2) {

        ListNode res = new ListNode(0);
		ListNode pointer = res;
        while ( lst1 != null && lst2 != null ){
            
			if( lst1.val < lst2.val ){
				res.next = lst1;
				lst1 = lst1.next;
			}else{
				
				res.next = lst2;
				lst2 = lst2.next;
			}

			res = res.next;
        }
        
        while ( lst1 != null ){
			res.next = lst1;
			res = res.next;
			lst1 = lst1.next;

		}
		
		while( lst2 != null ){
			res.next = lst2;
			res = res.next;
			lst2 = lst2.next;
		}
		
		return pointer.next;

    }
    public static void main(String[] args) {
		ListNode root = new ListNode(5);
		root.next = new ListNode(8);
		root.next.next = new ListNode(10);

		ListNode root1 = new ListNode(4);
		root1.next = new ListNode(11);
		root1.next.next = new ListNode(15);

		ListNode res = mergeTwoLists( root, root1 );

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
