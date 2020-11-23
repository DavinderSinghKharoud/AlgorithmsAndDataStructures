package algorithms.LeetCode;

public class ReverseLinkedList {

    //Iterative
    public static ListNode reverseList(ListNode head) {

        ListNode prev = null;
        ListNode curr = head;

        while ( curr != null ){

            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }

        return  prev;
    }

    public static void main(String[] args) {

        ListNode lst = new ListNode(1);
        lst.next = new ListNode(2);

        ListNode pr = reverseList( lst );

        printList( pr );

    }

    public static void printList( ListNode lst){
        while ( lst != null ){
            System.out.println( lst.val );
            lst = lst.next;
        }
    }

    // Watch this to understand https://www.youtube.com/watch?v=MRe3UsRadKw
    //Recursive Solution
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }


}
