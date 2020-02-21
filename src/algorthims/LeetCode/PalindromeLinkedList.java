package algorthims.LeetCode;

import java.util.Stack;

public class PalindromeLinkedList {
    //Brute Force
    private static boolean isPalindrome(ListNode head) {

        int len = 0;
        ListNode starting = head;
        while ( head != null ){
            len++;
            head = head.next;
        }

        System.out.println( starting.next.val );
        Stack<ListNode> stack = new Stack<>();
        int count = 0;
        while ( count != len/2 ){
            count++;
            stack.push( starting );
            starting = starting.next;
        }

        if( len % 2 != 0 ){
            starting = starting.next;
        }
        while ( starting != null ){

            int val = stack.pop().val;
            if( starting.val != val ){
                return false;
            }
            starting = starting.next;
        }

        return true;
    }
    public static void main(String[] args) {

        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        root.next.next = new ListNode(2);
        root.next.next.next = new ListNode(2);
        root.next.next.next.next = new ListNode(1);
        System.out.println( isPalindrome(root));
    }
}
