package algorthims.InterviewBit;


import java.util.HashSet;
import java.util.Set;

/**
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 *
 * For example, the following two linked lists:
 *
 *
 * A:          a1 → a2
 *                    ↘
 *                      c1 → c2 → c3
 *                    ↗
 * B:     b1 → b2 → b3
 *
 * begin to intersect at node c1.
 *
 *  Notes:
 * If the two linked lists have no intersection at all, return null.
 * The linked lists must retain their original structure after the function returns.
 * You may assume there are no cycles anywhere in the entire linked structure.
 * Your code should preferably run in O(n) time and use only O(1) memory.
 */
public class IntersectionOfLinkedList {

    //Time and Space O(N)
    public static ListNode getIntersectionNode(ListNode lst1, ListNode lst2) {

		Set<ListNode> set = new HashSet<>();
		
		ListNode traverse = lst1;
		
		while( traverse != null ){
			set.add( traverse );
			traverse = traverse.next;
		}
		
		traverse = lst2;
		
		while( traverse != null ){
			if( set.contains(traverse) ){
				return traverse;
			}
			traverse = traverse.next;
		}
		
		return null;
    }
    public static void main(String[] args) {

        ListNode root = new ListNode(2);
        root.next = new ListNode(4);
        root.next.next = new ListNode(3);

        ListNode root1 = new ListNode(5);
        root1.next = new ListNode(6);
        root1.next.next = root.next;

        ListNode res = getIntersectionNode1( root, root1 );

        System.out.println(res.val);
    }

    // Time complexity O(n)
    public static ListNode getIntersectionNode1(ListNode lst1, ListNode lst2) {
		
		int len1 = getLen(lst1);
		int len2 = getLen(lst2);
		
		ListNode traverse1 = ( len1 > len2 )? lst1: lst2;
		ListNode traverse2 = ( len2 < len1 ) ? lst2: lst1;
		
		int diff = ( len1 > len2 ) ? len1 - len2: len2 - len1;
		
		while( traverse1 != null && diff != 0){
			traverse1 = traverse1.next;
			diff--;
		}
		
		if( diff > 0 ) return null;
		
		while( traverse1 != null && traverse2 != null ){
			if( traverse1.equals(traverse2) ){
				return traverse1;
			}
			traverse1 = traverse1.next;
			traverse2 = traverse2.next;
		}
		
		return null;
	}
	
	public static int getLen( ListNode lst1 ){
		ListNode traverse = lst1;
		int len = 0;
		
		while( traverse != null ){
			len++;
			traverse = traverse.next;
		}
		
		return len;
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
