package InterviewBit;

/**
 * Problem Description
 *
 * Given a linked list FindGreatestCommonDivisor , reverse the order of all nodes at even positions.
 *
 *
 *
 * Problem Constraints
 * 1 <= Size of linked list <= 100000
 *
 *
 *
 * Input Format
 * First and only argument is the head of the Linked-List FindGreatestCommonDivisor.
 *
 *
 *
 * Output Format
 * Return the head of the new linked list.
 *
 *
 *
 * Example Input
 * Input 1:
 *
 * FindGreatestCommonDivisor = 1 -> 2 -> 3 -> 4
 * Input 2:
 *
 * FindGreatestCommonDivisor = 1 -> 2 -> 3
 *
 *
 * Example Output
 * Output 1:
 *
 *  1 -> 4 -> 3 -> 2
 * Output 2:
 *
 *  1 -> 2 -> 3
 *
 *
 * Example Explanation
 * Explanation 1:
 *
 * Nodes are positions 2 and 4 have been swapped.
 * Explanation 2:
 *
 * No swapping neccassary here.
 */
public class EvenReverse {

    public static ListNode solve(ListNode lst) {
		
		if( lst == null ) return null;
		
		ListNode traverse = lst;
		ListNode even = lst.next;
		ListNode temp = even;

		while(traverse != null && even != null){
			traverse.next = traverse.next.next;
			traverse = traverse.next;
			even.next = ( traverse !=  null ) ? traverse.next: null;
			even = even.next;

		}
		
		ListNode reverse = reverseList( temp );
		
		traverse = lst;
		while( traverse != null && reverse != null ){
			ListNode Next = traverse.next;
			traverse.next = reverse;

			ListNode reverseNext = reverse.next;
			reverse.next = Next;
			reverse = reverseNext;
			traverse = Next;
		}
		
		return lst;
        
    }
    
    public static ListNode reverseList( ListNode root ){
		if( root == null ) return null;
		
		ListNode previous = null;
		ListNode curr = root;
		ListNode Next = curr.next;
		
		while( Next != null ){
			curr.next = previous;
			previous = curr;
			curr = Next;
			Next = curr.next;
		}
		
		curr.next = previous;
		
		return curr;
	}
    public static void main(String[] args) {

		ListNode lst = new ListNode(1);
		lst.next = new ListNode(2);
		lst.next.next = new ListNode(3);
		lst.next.next.next = new ListNode(4);
		lst.next.next.next.next = new ListNode(5);
		lst.next.next.next.next.next = new ListNode(6);

		ListNode res = solve(lst);

		while ( res != null ){
			System.out.println(res.val);
			res = res.next;
		}

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
