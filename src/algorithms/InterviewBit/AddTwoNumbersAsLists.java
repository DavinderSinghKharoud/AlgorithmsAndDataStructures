package algorithms.InterviewBit;

/**
 * You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 *
 *     342 + 465 = 807
 * Make sure there are no trailing zeros in the output list
 * So, 7 -> 0 -> 8 -> 0 is not a valid response even though the value is still 807.
 */
public class AddTwoNumbersAsLists {

    public static ListNode addTwoNumbers(ListNode lst1, ListNode lst2) {

        int carry = 0;
        ListNode res = new ListNode(0);
        ListNode pointer = res;
		
		while( lst1 != null & lst2 != null ){
			int val1 = lst1.val;
			int val2 = lst2.val;
			
			int sum = val1 + val2 + carry;
			
			if( sum >= 10 ){
				carry = sum / 10;
				sum %= 10;
			}else{
				carry = 0;
			}
			
			pointer.next = new ListNode(sum);
			pointer = pointer.next;
			lst1 = lst1.next;
			lst2 = lst2.next;
			
		}
		
		while ( lst1 != null ){
			int val = lst1.val;
			int sum = val + carry;
			if( sum >= 10 ){
				carry = sum / 10;
				sum %= 10;
			}else{
				carry = 0;
			}
			
			pointer.next = new ListNode(sum);
			pointer = pointer.next;
			lst1 = lst1.next;
		}
		
		while( lst2 != null ){
			int val = lst2.val;
			int sum = val + carry;
			if( sum >= 10 ){
				carry = sum/10;
				sum %= 10;
			}else{
				carry = 0;
			}


			pointer.next = new ListNode(sum);
			pointer = pointer.next;
			lst2 = lst2.next;
		}

		if( carry > 0 ){
			pointer.next = new ListNode(carry);
		}
		return res.next;
  
    }
    public static void main(String[] args) {

		ListNode root = new ListNode(2);
		root.next = new ListNode(4);
		root.next.next = new ListNode(3);

		ListNode root1 = new ListNode(5);
		root1.next = new ListNode(6);
		root1.next.next = new ListNode(4);

		ListNode res = addTwoNumbers( root, root1 );

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
