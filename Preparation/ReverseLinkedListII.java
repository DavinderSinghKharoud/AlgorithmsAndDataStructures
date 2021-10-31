package Preparation;

public class ReverseLinkedListII {

    public static void main(String[] args) {
        ListNode first = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        first.next = second;
        second.next = third;
        ListNode ans = new ReverseLinkedListII().reverseBetween(first, 1, 2);
        while (ans != null) {
            System.out.println(ans.val);
            ans = ans.next;
        }
    }

    static class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode reverseBetween(ListNode lst, int m, int n) {

        ListNode prev = null;
        int count = m - 1;
        ListNode temp = lst;
        while (count-- > 0) {
            prev = temp;
            temp = temp.next;
        }

        ListNode node = getReverse(temp, n - m);
        if (prev != null) {
            prev.next = node;
            return lst;
        }
        return node;

    }

    ListNode getReverse(ListNode node, int k) {
        if (node == null) return null;
        ListNode temp = node.next, prev = node;
        while (k-- > 0) {
            ListNode next = (temp == null) ? null : temp.next;

            //change pointers
            temp.next = prev;

            //move
            prev = temp;
            temp = next;

        }
        node.next = temp;
        return prev;
    }
}
