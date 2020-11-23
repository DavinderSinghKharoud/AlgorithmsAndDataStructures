package algorithms.InterviewBit;

/**
 * Problem Description
 *
 * Given a linked list A of length N and an integer B.
 *
 * You need to reverse every alternate B nodes in the linked list A.
 *
 *
 *
 * Problem Constraints
 * 1 <= N <= 105
 * 1<= Value in Each Link List Node <= 103
 * 1 <= B <= N
 * N is divisible by B
 *
 *
 * Input Format
 * First argument is the head pointer of the linkedlist A.
 *
 * Second argument is an integer B.
 *
 *
 *
 * Output Format
 * Return the head pointer of the final linkedlist as described.
 *
 *
 *
 * Example Input
 * Input 1:
 *
 *  A = 3 -> 4 -> 7 -> 5 -> 6 -> 6 -> 15 -> 61 -> 16
 *  B = 3
 * Input 2:
 *
 *  A = 1 -> 4 -> 6 -> 6 -> 4 -> 10
 *  B = 2
 *
 *
 * Example Output
 * Output 1:
 *
 *  7 -> 4 -> 3 -> 5 -> 6 -> 6 -> 16 -> 61 -> 15
 * Output 2:
 *
 *  4 -> 1 -> 6 -> 6 -> 10 -> 4
 *
 *
 * Example Explanation
 * Explanation 1:
 *
 *  The linked list contains 9 nodes and we need to reverse alternate 3 nodes.
 *  First sublist of length 3  is 3 -> 4 -> 7 so on reversing it we get 7 -> 4 -> 3.
 *  Second sublist of length 3 is 5 -> 6 -> 6 we don't need to reverse it.
 *  Third sublist of length 3 is 15 -> 61 -> 16 so on reversing it we get 16 -> 61 -> 15.
 * Explanation 2:
 *
 *  The linked list contains 6 nodes and we need to reverse alternate 2 nodes.
 *  First sublist of length 2 is 1 -> 4 so on reversing it we get 4 -> 1.
 *  Second sublist of length 2 is 6 -> 6 we don't need to reverse it.
 *  Third sublist of length 2 is 4 -> 10 so on reversing it we get 10 -> 4.
 */
public class ReverseAlternateKNodes {

    public static ListNode solve(ListNode lst, int n) {
		
		int count = 0;
		if( n == 1 ) return lst;
		ListNode res = new ListNode(0);
		ListNode result = res;
		ListNode curr = lst;
		ListNode start = lst;
		ListNode end;
		
		while( curr != null ){
			
			count++;
			if( count == n ){
				count = 0;
				end = curr;
				curr = curr.next;
                res.next = reverse(start, end);
				res = start;

				start.next = curr;
				int temp = 0;
				while( temp != n && curr != null){
					temp++;
					curr = curr.next;
					if( temp == n){
						start = curr;
						break;
					}
					res = curr;
				}

            continue;
			}

            curr = curr.next;
		}
		
		return result.next;
		
    }

    private static ListNode reverse(ListNode start, ListNode end) {
        if( start == null ) return  null;
        ListNode previous = null;
        ListNode curr = start;
        ListNode Next = curr.next;

        while ( Next != null && curr != end ){
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

		ListNode res = solve(lst, 6);

		while ( res != null ) {
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
