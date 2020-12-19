package Algorithms.InterviewBit;

/**
 * Problem Description
 *
 * Given a Linked List A consisting of N nodes.
 *
 * The Linked List is binary i.e data values in the linked list nodes consist of only 0's and 1's.
 *
 * You need to sort the linked list and return the new linked list.
 *
 * NOTE:
 *
 * Try to do it in constant space.
 *
 *
 * Problem Constraints
 * 1 <= N <= 105
 *
 * A.val = 0 or A.val = 1
 *
 *
 *
 * Input Format
 * First and only argument is the head pointer of the linkedlist A.
 *
 *
 *
 * Output Format
 * Return the head pointer of the new sorted linked list.
 *
 *
 *
 * Example Input
 * Input 1:
 *
 *  1 -> 0 -> 0 -> 1
 * Input 2:
 *
 *  0 -> 0 -> 1 -> 1 -> 0
 *
 *
 * Example Output
 * Output 1:
 *
 *  0 -> 0 -> 1 -> 1
 * Output 2:
 *
 *  0 -> 0 -> 0 -> 1 -> 1
 *
 *
 * Example Explanation
 * Explanation 1:
 *
 *  The sorted linked list looks like:
 *   0 -> 0 -> 1 -> 1
 * Explanation 2:
 *
 *  The sorted linked list looks like:
 *   0 -> 0 -> 0 -> 1 -> 1
 */
public class SortBinaryLinkedList {

    public static ListNode solve(ListNode root) {
		
		int countZero = 0;
		int countOne = 0;
		
		ListNode traverse = root;
		
		while( traverse != null ){
			if( traverse.val == 0 ){
				countZero++;
			}else{
				countOne++;
			}
			traverse = traverse.next;
		}
		
		traverse = root;
		
		while( countZero != 0 ){
			traverse.val = 0;
			traverse = traverse.next;
			countZero--;
		}
		
		while( countOne != 0 ){
			traverse.val = 1;
			traverse = traverse.next;
			countOne--;
		}
		
		return root;
    }
    public static void main(String[] args) {

        ListNode lst = new ListNode(1);
        lst.next = new ListNode(0);
        lst.next.next = new ListNode(1);
		lst.next.next.next = new ListNode(0);
		lst.next.next.next.next = new ListNode(0);

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
