package InterviewBit;

/**
 * Given a singly linked list, determine if its a palindrome. Return 1 or 0 denoting if its a palindrome or not, respectively.
 *
 * Notes:
 *
 * Expected solution is linear in time and constant in space.
 * For example,
 *
 * List 1-->2-->1 is a palindrome.
 * List 1-->2-->3 is not a palindrome.
 */
public class PalindromeList {

    public static int lPalin(ListNode root) {
		
		if( root == null ) return -1;
		
		int len = getLen(root);
		
		int middle = ( len + 1 )/2;
		
		ListNode traverse = root;
		
		ListNode startReverse;
		while( traverse != null && middle != 0 ){
			middle--;
			traverse = traverse.next;
		}
		
		startReverse = traverse;
		ListNode end = reverseList(startReverse);
		
		ListNode start1 = root;
		ListNode start2 = end;
		
		while( start1 != null && start2 != null ){
			if( start1.val != start2.val ){
				return 0;
			}
			start1 = start1.next;
			start2 = start2.next;
		}
		
		return 1;
		
    }
    
    public static int getLen( ListNode root ){
		int len = 0;
		ListNode traverse = root;
		
		while( traverse != null ){
			traverse = traverse.next;
			len++;
		}
		
		return len;
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
		lst.next.next.next = new ListNode(2);
		lst.next.next.next.next = new ListNode(1);

		System.out.println( lPalin(lst));
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
