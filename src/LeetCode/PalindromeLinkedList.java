package LeetCode;

import java.util.Stack;

/**
 * Given a singly linked list, determine if it is a palindrome.
 *
 * Example 1:
 *
 * Input: 1->2
 * Output: false
 */
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

    private static boolean isPalindromeSlowAndFast(ListNode head) {

        if( head == null || head.next == null ){
            return  true;
        }

        ListNode prevSlowNode = null;
        ListNode slowNode = head;
        ListNode fastNode = head;

        while ( fastNode != null && fastNode.next != null ){

            fastNode = fastNode.next.next;

            ListNode nextSlowNode = slowNode.next;
            slowNode.next = prevSlowNode;
            prevSlowNode = slowNode;
            slowNode = nextSlowNode;
        }

        ListNode firstHalfPointer = prevSlowNode;

        ListNode secondHalfPointer = null;

        //Even length
        if( fastNode == null ){
            secondHalfPointer = slowNode;
        }else{
            secondHalfPointer = slowNode.next;
        }

        while ( firstHalfPointer != null && secondHalfPointer != null ){
            if( firstHalfPointer.val != secondHalfPointer.val ){
                return false;
            }
            firstHalfPointer = firstHalfPointer.next;
            secondHalfPointer = secondHalfPointer.next;
        }

        return true;
    }

}
