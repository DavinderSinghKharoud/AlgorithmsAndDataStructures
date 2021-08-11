package InterviewBit;

/**
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.
 * <p>
 * For example:
 * Given 1->2->3->4->5->NULL, m = 2 and n = 4,
 * <p>
 * return 1->4->3->2->5->NULL.
 * <p>
 * Note:
 * Given m, n satisfy the following condition:
 * 1 ≤ m ≤ n ≤ length of list. Note 2:
 * Usually the version often seen in the interviews is reversing the whole linked list which is obviously an easier version of this question.
 */
public class ReverseLinkedListII {

    //Time complexity O(n)
    public static ListNode reverseBetween(ListNode lst, int m, int n) {

        if (lst == null) return null;
        ListNode traverse = lst;
        ListNode start = null;
        ListNode end = null;
        ListNode middle1 = null;
        ListNode middle2 = null;
        int count = 0;

        while (traverse != null) {
            count++;

            if (count == m - 1) {
                middle1 = traverse;
            }
            if (count == m) {
                start = traverse;
            }
            if (count == n + 1) {
                middle2 = traverse;
                break;
            }
            if (count == n) {
                end = traverse;
            }
            traverse = traverse.next;
        }


        if (start == null || end == null) return lst;

        reverseList(start, end);

        if (middle1 == null) {
            lst = end;
        } else {
            middle1.next = end;
        }
        start.next = middle2;

        return lst;

    }


    public static void reverseList(ListNode start, ListNode end) {

        ListNode previous = null;
        ListNode curr = start;
        ListNode adjacent = (start != null && start.next != null) ? start.next : null;

        while (adjacent != null && curr != end) {
            curr.next = previous;
            previous = curr;
            curr = adjacent;
            adjacent = curr.next;
        }

        assert curr != null;
        if (curr == end) {
            curr.next = previous;
        }

    }

    public static void main(String[] args) {

        ListNode lst = new ListNode(1);
        lst.next = new ListNode(2);
        lst.next.next = new ListNode(3);
//		lst.next.next.next = new ListNode(4);
//		lst.next.next.next.next = new ListNode(5);

        ListNode res = reverseBetween2(lst, 1, 2);

        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

    public static ListNode reverseBetween2(ListNode lst, int m, int n) {
        ListNode start = null, res = lst;
        if( lst == null || lst.next == null ) return lst;
        int count = 1;

        while ( count < m ){
            start = lst;
            lst = lst.next;
            count++;
        }

        ListNode prev = reverse( lst, n - m + 1 );
        if( start == null ) return prev;

        start.next = prev;
        return res;

    }

    private static ListNode reverse(ListNode lst, int n) {

        ListNode prev = null;
        ListNode curr = lst, next;

        int count = 1;

        while ( count <= n && curr != null ){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            count++;
        }

        lst.next = curr; //Attach with the end
        return prev;

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
