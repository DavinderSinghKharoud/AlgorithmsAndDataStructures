package Algorithms.LeetCode;


public class AddTwoNumbers {

    //Brute Force
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummyHead = new ListNode(0);

        ListNode curr = dummyHead;
        int carry = 0;

        while ( l1 != null || l2 != null ){
            int x = ( l1 != null )? l1.val : 0;
            int y = ( l2 != null )? l2.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;

            curr.next = new ListNode( sum % 10 );
            curr = curr.next;

            if( l1 != null ) l1 = l1.next;
            if( l2 != null ) l2 = l2.next;

        }

        if( carry > 0 ){
            curr.next = new ListNode( carry );
        }

        return dummyHead.next;
    }

    //Recursive Solution
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        return addList(l1, l2);
    }

    public static ListNode addList(ListNode l1, ListNode l2){
        return _addList(l1, l2, new ListNode(0));
    }

    public static ListNode _addList(ListNode l1, ListNode l2, ListNode output){
        if(l1 != null || l2 != null || output.val != 0){
            int l1Val = l1 != null ? l1.val : 0;
            int l2Val = l2 != null ? l2.val : 0;
            ListNode l1NNode = l1 != null ? l1.next : null;
            ListNode l2NNode = l2 != null ? l2.next : null;
            int sum = l1Val + l2Val + output.val;
            output.val = sum%10;
            output.next = _addList(l1NNode, l2NNode, new ListNode(sum/10));
            return output;
        }
        return output.val != 0 ? output : null;
    }

    public static void main(String[] args) {

        ListNode l1 = new ListNode(5);
        l1.next = new ListNode(6);
        l1.next.next = new ListNode(7);

        ListNode l2 = new ListNode(6);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(7);


       ListNode result = addTwoNumbers( l1, l2);


       while ( result != null ){
           System.out.println( result.val );
           result = result.next;
       }
    }
}
