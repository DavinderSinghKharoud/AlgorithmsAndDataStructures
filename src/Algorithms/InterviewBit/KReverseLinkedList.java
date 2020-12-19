package Algorithms.InterviewBit;

/**
 * Given a singly linked list and an integer K, reverses the nodes of the
 *
 * list K at a time and returns modified linked list.
 *
 *  NOTE : The length of the list is divisible by K
 * Example :
 *
 * Given linked list 1 -> 2 -> 3 -> 4 -> 5 -> 6 and K=2,
 *
 * You should return 2 -> 1 -> 4 -> 3 -> 6 -> 5
 *
 * Try to solve the problem using constant extra space.
 */
public class KReverseLinkedList {

    public static ListNode reverseList(ListNode lst, int n) {
        ListNode res = new ListNode(0);
        ListNode temp = res;
        ListNode curr = lst;
        ListNode start = lst;
        ListNode end;

        int count = 0;

        while (curr != null) {
            count++;
            if (count == n) {
                count = 0;
                end = curr;
                curr = curr.next;
                res.next = reverse(start, end);
                res = start;
                start = curr;
                continue;
            }
            curr = curr.next;

        }

        if( count != 0 ){
            end = reverse(start, null);
            res.next = end;
        }


        return temp.next;

    }

    private static ListNode reverse(ListNode start, ListNode end) {
        if( start == null ) return null;

        ListNode previous = null;
        ListNode curr = start;
        ListNode Next = start.next;

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

        ListNode res = reverseList(lst, 3);

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
