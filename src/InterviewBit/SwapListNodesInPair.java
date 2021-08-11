package InterviewBit;

public class SwapListNodesInPair {

    /**
     * Given a linked list, swap every two adjacent nodes and return its head.
     *
     * For example,
     * Given 1->2->3->4, you should return the list as 2->1->4->3.
     *
     * Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
     * @param root
     * @return
     */
    public static ListNode swapPairs(ListNode root) {

        ListNode temp = root;

        while ( temp != null && temp.next != null ){

            //Swap the values
            int curr = temp.val;
            temp.val = temp.next.val;
            temp.next.val = curr;

            temp = temp.next.next;
        }

        return root;
    }

    private static ListNode swap(ListNode previous, ListNode next) {
        if( previous == null ) return next;
        if( next == null ) return previous;

        next.next = previous;
        return previous;
    }


    public static void main(String[] args) {

        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        root.next.next = new ListNode(3);
        root.next.next.next = new ListNode(4);
        root.next.next.next.next = new ListNode(5);
        root.next.next.next.next.next = new ListNode(6);

        ListNode res = swapPairs(root);

        while ( res != null ){
            System.out.println(res.val);
            res = res.next;
        }
    }

    public static ListNode swapPairs2(ListNode root) {

        if( root == null ) return null;

        ListNode next = root.next;

        if( next == null ) return root;
        ListNode nextNext = next.next;

        next.next = root; //swap
        root.next = swapPairs2( nextNext );

        return next;
    }

    static class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
