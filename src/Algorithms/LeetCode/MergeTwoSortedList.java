package Algorithms.LeetCode;

/**
 * Merge two sorted linked lists and return it as a new list.
 * The new list should be made by splicing together the nodes of the first two lists.
 *
 * Example:
 *
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 */
public class MergeTwoSortedList {
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode finalList;

        if( l1 == null ){
            return l2;
        }else if( l2 == null ){
            return l1;
        }
        if( l1.val < l2.val ){
            finalList = new ListNode(l1.val);
            l1 = l1.next;
        }else{
            finalList = new ListNode(l2.val);
            l2 = l2.next;
        }

        ListNode temp = finalList;

        while ( l1 != null && l2 != null){

            if( l1.val < l2.val ){
                finalList.next = new ListNode(l1.val);
                l1 = l1.next;
                finalList = finalList.next;
            }else{
                finalList.next = new ListNode(l2.val);
                l2 = l2.next;
                finalList = finalList.next;
            }
        }

        while ( l1 != null ){
            finalList.next = new ListNode( l1.val );
            finalList = finalList.next;
            l1 = l1.next;
        }
        while ( l2 != null ){
            finalList.next = new ListNode( l2.val );
            finalList = finalList.next;
            l2 = l2.next;
        }

        return temp;
    }
    public static void main(String[] args) {

        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode finalList = mergeTwoLists( l1, l2);

        printList( finalList );
    }

    private static void printList( ListNode root ){

        while ( root != null ){
            System.out.println( root.val );
            root = root.next;
        }
    }
}
